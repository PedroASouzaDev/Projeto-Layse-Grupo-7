package com.auroraapp.view.pages;

import com.auroraapp.model.Categoria;
import com.auroraapp.model.Evento;
import com.auroraapp.view.Router;
import com.auroraapp.view.components.ContentList;
import com.auroraapp.view.components.FilterSidebar;
import com.auroraapp.view.components.HeaderBar;
import com.auroraapp.view.components.SearchHeader;
import com.auroraapp.view.components.EventoForm;
import com.auroraapp.view.hooks.EventoHttpHook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class HomeView extends BorderPane {

    private static final ObservableList<Evento> eventos = FXCollections.observableArrayList();
    private static final EventoHttpHook eventoHttp = new EventoHttpHook(eventos);

    public HomeView(Router router) {

        HeaderBar header = new HeaderBar();
        setTop(header);

        FilterSidebar sidebar = new FilterSidebar();
        setLeft(sidebar);

        VBox center = new VBox();
        center.setBackground(new Background(
                new BackgroundFill(Color.web("#e2e8f0"), CornerRadii.EMPTY, Insets.EMPTY)));
        center.setPadding(new Insets(14, 14, 0, 14));
        center.setSpacing(8);

        HBox tableHeader = new HBox();
        tableHeader.setAlignment(Pos.CENTER_RIGHT);

        SearchHeader search = new SearchHeader();
        HBox.setHgrow(search, Priority.ALWAYS);

        eventoHttp.fetchEventos();

        ContentList content = new ContentList();
        FilteredList<Evento> filtered = new FilteredList<>(eventos, e -> true);
        content.setEvents(filtered);

        updateSidebarCategories(eventos, sidebar);
        eventos.addListener((javafx.collections.ListChangeListener<Evento>) change -> {
            updateSidebarCategories(eventos, sidebar);
        });

        Button criarEventoBotao = new Button("Criar Evento");
        criarEventoBotao.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 24px; -fx-padding: 8px 32px; -fx-font-weight: bold;");
        criarEventoBotao.setOnMouseEntered(e -> criarEventoBotao.setStyle(
                "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 24px; -fx-padding: 8px 32px; -fx-font-weight: bold;"));
        criarEventoBotao.setOnMouseExited(e -> criarEventoBotao.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 24px; -fx-padding: 8px 32px; -fx-font-weight: bold;"));

        criarEventoBotao.setOnAction(evt -> {
            EventoForm form = new EventoForm(eventoHttp);
            Scene dialogScene = new Scene(form);
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            Window owner = this.getScene() != null ? this.getScene().getWindow() : null;
            if (owner != null) dialog.initOwner(owner);
            dialog.setTitle("Criar Evento");
            dialog.setScene(dialogScene);
            dialog.setResizable(false);
            dialog.showAndWait();
        });

        tableHeader.getChildren().addAll(search, criarEventoBotao);

        Runnable updatePredicate = () -> {
            final String q = search.getText() == null ? "" : search.getText().trim().toLowerCase();
            final ObservableList<String> selectedCats = sidebar.getSelectedCategoryNames();

            filtered.setPredicate(ev -> {
                boolean nameMatches = q.isEmpty() || (ev.getNome() != null && ev.getNome().toLowerCase().contains(q));

                if (selectedCats == null || selectedCats.isEmpty() || selectedCats.contains("TODAS")) {
                    return nameMatches;
                }

                boolean categoryMatches = ev.getCategorias().stream()
                        .map(Categoria::getNome)
                        .anyMatch(selectedCats::contains);

                return nameMatches && categoryMatches;
            });
        };

        search.textProperty().addListener((obs, o, n) -> updatePredicate.run());
        sidebar.getSelectedCategoryNames()
                .addListener((javafx.collections.ListChangeListener<String>) c -> updatePredicate.run());

        VBox.setVgrow(content, Priority.ALWAYS);
        center.getChildren().addAll(tableHeader, content);

        HBox centerWrapper = new HBox(center);
        centerWrapper.setStyle("-fx-padding: 18;");
        setCenter(centerWrapper);
        setMargin(centerWrapper, new Insets(0, 20, 0, 20));
    }

    private void updateSidebarCategories(ObservableList<Evento> eventos, FilterSidebar sidebar) {
        ObservableList<String> categorias = FXCollections.observableArrayList();

        for (Evento e : eventos) {
            if (e.getCategorias() == null) continue;
            for (Categoria c : e.getCategorias()) {
                if (c == null || c.getNome() == null) continue;
                if (!categorias.contains(c.getNome())) {
                    categorias.add(c.getNome());
                }
            }
        }

        if (!categorias.contains("TODAS")) {
            categorias.add(0, "TODAS");
        } else {
            categorias.remove("TODAS");
            categorias.add(0, "TODAS");
        }

        sidebar.setCategoryNames(categorias);
    }
}
