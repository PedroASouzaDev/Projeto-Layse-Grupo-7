package com.auroraapp.view.components;

import com.auroraapp.model.Evento;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EventRow extends HBox {
    public EventRow(Evento evento) {
        setPadding(new Insets(8));
        setSpacing(12);
        setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 8px; " +      
            "-fx-border-radius: 8px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0, 0, 0); " + 
            "-fx-cursor: hand;" 
        );

        setOnMouseEntered(e -> setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 8px; " +
            "-fx-border-radius: 8px; " +
            "-fx-effect: dropshadow(gaussian, rgba(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 4), 15, 0, 0, 0);" +
            "-fx-cursor: hand;" 
        ));

        setOnMouseExited(e -> setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 8px; " +
            "-fx-border-radius: 8px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0, 0, 0);" +
            "-fx-cursor: hand;" 
        ));


        Label title = new Label(evento.getNome());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: black;");

        Label desc = new Label("R$" + String.valueOf(evento.getValorIngresso()));
        desc.setStyle("-fx-text-fill: black;");

        VBox v = new VBox(4, title, desc);
        getChildren().add(v);
    }
}