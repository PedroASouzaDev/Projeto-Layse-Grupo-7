package com.auroraapp.view.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FilterSidebar extends VBox {
    private final ListView<String> categories = new ListView<>();

    public FilterSidebar() {
        setPadding(new Insets(12));
        setSpacing(12);
        setStyle("-fx-background-color: #efefef;");
        setPrefWidth(180);

        Label header = new Label("Filtros");
        header.setStyle("-fx-font-weight: bold; -fx-padding: 6 0 0 6;");

        categories.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        categories.setPrefHeight(120);

        VBox.setVgrow(categories, Priority.ALWAYS);
        getChildren().addAll(header, categories);

        categories.setItems(FXCollections.observableArrayList("categorias", "Categoria A", "Categoria B", "Categoria C"));
    }

    // adicionar categorias ao ListView
    public void setCategoryNames(ObservableList<String> names) {
        categories.setItems(names);
    }

    // expor nomes de categorias selecionadas (observável)
    public ObservableList<String> getSelectedCategoryNames() {
        return categories.getSelectionModel().getSelectedItems();
    }
}