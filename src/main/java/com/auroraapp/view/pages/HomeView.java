package com.auroraapp.view.pages;

import com.auroraapp.view.Router;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {
    public HomeView(Router router) {
        super(10);
        Label title = new Label("Home");
        Button toReports = new Button("Go to Reports");
        toReports.setOnAction(e -> router.navigate("reports"));
        getChildren().addAll(title, toReports);
    }
}