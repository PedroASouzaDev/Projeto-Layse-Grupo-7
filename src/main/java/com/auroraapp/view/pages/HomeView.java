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

    // 🔹 Lista observável de eventos
    private final ObservableList<Evento> eventos = FXCollections.observableArrayList();

    public HomeView(Router router) {

        // 🔹 Header da tela
        HeaderBar header = new HeaderBar();
        setTop(header);

        // 🔹 Sidebar de filtros (categorias)
        FilterSidebar sidebar = new FilterSidebar();
        setLeft(sidebar);

        // 🔹 Layout central (conteúdo principal)
        VBox center = new VBox();
        center.setBackground(new Background(
                new BackgroundFill(Color.web("#e2e8f0"), CornerRadii.EMPTY, Insets.EMPTY)));
        center.setPadding(new Insets(14, 14, 0, 14));
        center.setSpacing(8);

        // 🔹 Header da tabela (barra de busca + botão criar evento)
        HBox tableHeader = new HBox();
        tableHeader.setAlignment(Pos.CENTER_RIGHT);

        // 🔹 Campo de busca
        SearchHeader search = new SearchHeader();
        HBox.setHgrow(search, Priority.ALWAYS);

        // 🔹 Hook para buscar eventos no backend
        EventoHttpHook eventoHttp = new EventoHttpHook();

        // 🔹 Lista de eventos filtrados (FilteredList) e ContentList
        ContentList content = new ContentList();
        FilteredList<Evento> filtered = new FilteredList<>(eventos, e -> true);
        content.setEvents(filtered);

        // 🔹 Carregar eventos automaticamente ao abrir a tela
        eventoHttp.fetchEventos(eventos);

        // 🔹 Botão criar evento
        Button criarEventoBotao = new Button("Criar Evento");
        criarEventoBotao.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 24px; -fx-padding: 8px 32px; -fx-font-weight: bold;");
        criarEventoBotao.setOnMouseEntered(e -> criarEventoBotao.setStyle(
                "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-background-radius: 24px; -fx-padding: 8px 32px; -fx-font-weight: bold;"));
        criarEventoBotao.setOnMouseExited(e -> criarEventoBotao.setStyle(
                "-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-background-radius: 24px; -fx-padding: 8px 32px; -fx-font-weight: bold;"));

        // 🔹 Abrir modal para criar novo evento
        criarEventoBotao.setOnAction(evt -> {
            EventoForm form = new EventoForm();
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

        // 🔹 Adicionar campo de busca e botão criar evento ao header da tabela
        tableHeader.getChildren().addAll(search, criarEventoBotao);

        // 🔹 Filtragem dinâmica por nome e categorias
        Runnable updatePredicate = () -> {
            final String q = search.getText() == null ? "" : search.getText().trim().toLowerCase();
            final ObservableList<String> selectedCats = sidebar.getSelectedCategoryNames();

            filtered.setPredicate(ev -> {
                // 🔹 Filtro por nome do evento
                boolean nameMatches = q.isEmpty() || (ev.getNome() != null && ev.getNome().toLowerCase().contains(q));

                // 🔹 Filtro por categorias selecionadas
                if (selectedCats == null || selectedCats.isEmpty() || selectedCats.contains("TODAS")) {
                    return nameMatches;
                }

                boolean categoryMatches = ev.getCategorias().stream()
                        .map(Categoria::getNome)
                        .anyMatch(selectedCats::contains);

                return nameMatches && categoryMatches;
            });
        };

        // 🔹 Listeners para atualização da filtragem em tempo real
        search.textProperty().addListener((obs, o, n) -> updatePredicate.run());
        sidebar.getSelectedCategoryNames()
                .addListener((javafx.collections.ListChangeListener<String>) c -> updatePredicate.run());

        // 🔹 Configuração de crescimento e layout do conteúdo
        VBox.setVgrow(content, Priority.ALWAYS);
        center.getChildren().addAll(tableHeader, content);

        // 🔹 Wrapper central e margens
        HBox centerWrapper = new HBox(center);
        centerWrapper.setStyle("-fx-padding: 18;");
        setCenter(centerWrapper);
        setMargin(centerWrapper, new Insets(0, 20, 0, 20));
    }
}
