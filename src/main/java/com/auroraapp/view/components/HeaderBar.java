package com.auroraapp.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class HeaderBar extends HBox {
    public HeaderBar() {
        setPadding(new Insets(12, 20, 12, 20));
        setStyle("-fx-background-color: white;");
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(10);

        Label title = new Label();
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        getChildren().addAll(title, spacer);
    }
}