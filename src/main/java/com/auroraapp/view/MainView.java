package com.auroraapp.view;

import com.auroraapp.view.pages.Estatisticas;
import com.auroraapp.view.pages.HomeView;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainView {
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Router router = new Router(root);

        router.register("home", () -> new HomeView(router));
        router.register("estatisticas", () -> new Estatisticas(router));

        root.setBackground(new Background(
            new BackgroundFill(Color.web("#e2e8f0"), CornerRadii.EMPTY, Insets.EMPTY)
        ));

        // start at home
        router.navigate("home");

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Relatorios e Estatisticas");
        stage.setScene(scene);
        stage.show();
    }
}
