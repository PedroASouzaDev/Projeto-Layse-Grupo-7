package com.auroraapp.view.pages;

import com.auroraapp.model.Categoria;
import com.auroraapp.model.Evento;
import com.auroraapp.view.Router;
import com.auroraapp.view.components.ContentList;
import com.auroraapp.view.components.FilterSidebar;
import com.auroraapp.view.components.HeaderBar;
import com.auroraapp.view.components.SearchHeader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.LinkedList;

public class HomeView extends BorderPane {
    public HomeView(Router router) {
        // Header
        HeaderBar header = new HeaderBar();
        setTop(header);

        // Filtros Lateral
        FilterSidebar sidebar = new FilterSidebar();
        setLeft(sidebar);

        // Conteudo Central (Pesquisa e Lista)
        VBox center = new VBox();
        center.setPadding(new Insets(14));
        center.setSpacing(8);

        SearchHeader search = new SearchHeader();
        ContentList content = new ContentList();

        ObservableList<Evento> eventos = FXCollections.observableArrayList();

        // Eventos de exemplo (Futura integração com a API)
        eventos.add(new Evento(
            1,
            "Feira de Tecnologia",
            LocalDate.of(2025, 11, 10),
            LocalDate.of(2025, 11, 12),
            50.0,
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>()
        ));

        eventos.add(new Evento(
            2,
            "Concerto ao Ar Livre",
            LocalDate.of(2025, 12, 5),
            LocalDate.of(2025, 12, 5),
            120.0,
            new LinkedList<>(),
            new LinkedList<>(),
            new LinkedList<>()
        ));

        LinkedList<Categoria> categoras = new LinkedList<>();
        categoras.add(new Categoria(0, "Fotografia"));

        eventos.add(new Evento(
            3,
            "Workshop de Fotografia",
            LocalDate.of(2026, 1, 15),
            LocalDate.of(2026, 1, 16),
            80.0,
            new LinkedList<>(),
            new LinkedList<>(),
            categoras
        ));

        ObservableList<String> categorias = FXCollections.observableArrayList();

        // Categorias de exemplo (Futura integração com a API)
        categorias.add("TODAS");
        categorias.add("Musica");
        categorias.add("Fotografia");
        categorias.add("Carros");
        categorias.add("Educação");
        categorias.add("ASDLKJHSALK");

        if (!categorias.isEmpty()) {
            sidebar.setCategoryNames(categorias);
        }

        // Filtragem
        FilteredList<Evento> filtered = new FilteredList<>(eventos, e -> true);
        content.setEvents(filtered);

        Runnable updatePredicate = () -> {
            final String q = search.getText() == null ? "" : search.getText().trim().toLowerCase();
            final ObservableList<String> selectedCats = sidebar.getSelectedCategoryNames();

            filtered.setPredicate(ev -> {
                // name filter
                boolean nameMatches = q.isEmpty() || (ev.getNome() != null && ev.getNome().toLowerCase().contains(q));

                if (selectedCats == null || selectedCats.isEmpty() || selectedCats.contains("TODAS")) {
                    return nameMatches;
                }

                boolean categoryMatches = ev.getCategorias().stream()
                    .map(cat -> {
                        if (cat instanceof Categoria) {
                            return ((Categoria) cat).getNome();
                        }
                        try { return (String) cat.getClass().getMethod("getNome").invoke(cat); }
                        catch (Exception ex) { return cat.toString(); }
                    })
                    .anyMatch(selectedCats::contains);

                return nameMatches && categoryMatches;
            });
        };

        // Listeners
        search.textProperty().addListener((obs, o, n) -> updatePredicate.run());
        sidebar.getSelectedCategoryNames().addListener((javafx.collections.ListChangeListener<String>) c -> updatePredicate.run());

        VBox.setVgrow(content, Priority.ALWAYS);
        center.getChildren().addAll(search, content);

        HBox centerWrapper = new HBox(center);
        centerWrapper.setStyle("-fx-background-color: #efefef; -fx-padding: 18;");
        setCenter(centerWrapper);

        setMargin(centerWrapper, new Insets(0, 20, 0, 20));
    }
}