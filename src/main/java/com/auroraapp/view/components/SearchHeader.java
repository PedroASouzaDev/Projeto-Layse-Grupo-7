package com.auroraapp.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchHeader extends HBox {
    private final TextField searchField = new TextField();

    public SearchHeader() {
        setPadding(new Insets(18, 0, 18, 0));
        setAlignment(Pos.CENTER_LEFT);
        setStyle(
            "-fx-background-color: transparent;"
        );

        searchField.setPromptText("BUSCA POR NOME");
        searchField.setMaxWidth(Double.MAX_VALUE);
        searchField.setStyle(
            "-fx-background-color: white; " +
            "-fx-padding: 12 20 12 20; " +
            "-fx-background-radius: 3; " +
            "-fx-border-radius: 3; " +
            "-fx-font-size: 16px; " +
            "-fx-text-fill: #555;"
        );

        getChildren().add(searchField);
    }

    // expose text property so callers can listen / bind
    public javafx.beans.property.StringProperty textProperty() {
        return searchField.textProperty();
    }

    public String getText() {
        return searchField.getText();
    }

    public void setText(String text) {
        searchField.setText(text);
    }
}