package com.auroraapp.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainView {
    public void start(Stage stage) {
        StackPane root = new StackPane(new Label("HOLA"));
        Scene scene = new Scene(root, 400, 200);
        stage.setTitle("Relatorios e Estatisticas");
        stage.setScene(scene);
        stage.show();
    }
}
