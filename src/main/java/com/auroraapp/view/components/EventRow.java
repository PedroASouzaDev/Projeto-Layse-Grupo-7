package com.auroraapp.view.components;

import com.auroraapp.model.Relatorio;
import com.auroraapp.view.pages.EventoDetail;
import com.auroraapp.model.Evento;
import com.auroraapp.view.Router;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.io.File;

public class EventRow extends HBox {

    public EventRow(Evento evento, Router router) {
        setPadding(new Insets(8));
        setSpacing(12);
        setStyle(
            "-fx-background-color: white; " +
            "-fx-background-radius: 8px; " +      
            "-fx-border-radius: 8px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0, 0, 0); " + 
            "-fx-cursor: hand;"
        );
        alignmentProperty().set(Pos.CENTER);

        // --- Título e descrição ---
        Label title = new Label(evento.getNome());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 13px; -fx-text-fill: black;");

        Label desc = new Label("R$ " + String.valueOf(evento.getValorIngresso()));
        desc.setStyle("-fx-text-fill: black;");

        VBox vbox = new VBox(4, title, desc);

        // --- Espaço entre o texto e o botão ---
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // --- Botão de Relatório ---
        Button btnRelatorio = new Button("Relatório");
        btnRelatorio.setStyle(
            "-fx-background-color: #28a745; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 2;"
        );
        btnRelatorio.setVisible(false); // Esconde inicialmente
        btnRelatorio.alignmentProperty().set(Pos.CENTER);

        getChildren().addAll(vbox, spacer, btnRelatorio);

        // --- Efeitos de hover ---
        setOnMouseEntered(e -> {
            btnRelatorio.setVisible(true);
            setStyle(
                "-fx-background-color: white; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.35), 10, 0, 0, 3);" +
                "-fx-cursor: hand;"
            );
        });

        setOnMouseExited(e -> {
            btnRelatorio.setVisible(false);
            setStyle(
                "-fx-background-color: white; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 5, 0, 0, 0);" +
                "-fx-cursor: hand;"
            );
        });

        btnRelatorio.setOnAction(ev -> {
            try {
                File pasta = new File("relatorios");
                if (!pasta.exists()) pasta.mkdirs();

                String caminho = "relatorios/" + evento.getNome().replaceAll("\\s+", "_") + "_relatorio.csv";

                Relatorio relatorio = new Relatorio(evento);
                relatorio.gerarCSV(caminho);

                System.out.println(" Relatório CSV gerado: " + caminho);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println(" Erro ao gerar relatório: " + ex.getMessage());
            }
        });

        // --- Clique na row para abrir detalhes ---
        setOnMouseClicked(e -> {
            if (e.getTarget() instanceof Button) return; // Evita conflito com o botão
            // Navigate to the event detail page inside the main router
            EventoDetail detalhe = new EventoDetail(evento, router);
            router.navigateTo(detalhe.getView());
        });
    }
}
