package com.auroraapp.view.components;

import com.auroraapp.view.Router;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HeaderBar extends HBox {
    public HeaderBar(Router router) {
        setPadding(new Insets(12, 20, 12, 20));
        setStyle("-fx-background-color: #1e293b;");
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(30);

        Label home = criarBotao("Home", () -> router.navigate("home"));
        Label estatisticas = criarBotao("Estatísticas", () -> router.navigate("estatisticas"));


        getChildren().addAll(home, estatisticas);
    }

    private Label criarBotao(String texto, Runnable acao) {
        Label l = new Label(texto);
        l.setFont(Font.font(16));
        l.setTextFill(Color.WHITE);
        l.setStyle("-fx-cursor: hand;");

        l.setOnMouseEntered(e -> l.setTextFill(Color.LIGHTGRAY));
        l.setOnMouseExited(e -> l.setTextFill(Color.WHITE));
        l.setOnMouseClicked(e -> acao.run());

        return l;
    }
}