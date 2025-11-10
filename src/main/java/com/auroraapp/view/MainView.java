package com.auroraapp.view;

import com.auroraapp.view.pages.EventoDetail;
import com.auroraapp.view.pages.HomeView;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView {
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Router router = new Router(root);

        router.register("home", () -> new HomeView(router));

        root.setBackground(new Background(
            new BackgroundFill(Color.web("#e2e8f0"), CornerRadii.EMPTY, Insets.EMPTY)
        ));

        router.register("reports", () -> {
            Button back = new Button("Back");
            VBox v = new VBox(10, new Label("Reports and Statistics"), back);
            back.setOnAction(e -> router.navigate("home"));
            return v;
        });

        // start at home
        router.navigate("home");

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Relatorios e Estatisticas");
        stage.setScene(scene);
        stage.show();
    }
}
