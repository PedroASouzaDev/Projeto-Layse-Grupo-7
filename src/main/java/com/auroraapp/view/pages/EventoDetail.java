package com.auroraapp.view.pages;

import com.auroraapp.model.Evento;
import com.auroraapp.model.Categoria;
import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;
import com.auroraapp.view.Router;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class EventoDetail extends BorderPane {

    private final Evento evento;

    public EventoDetail(Evento evento, Router router) {
        this.evento = evento;

        // Formato de data
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Fundo principal
        setStyle("-fx-background-color: #e8edf5;");

        // --- Cabeçalho com botão voltar ---
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(10);
        header.setPadding(new Insets(20, 30, 10, 30));

        Button btnVoltar = new Button("← Voltar");
        btnVoltar.setStyle(
                "-fx-background-color: #3b82f6; " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 20; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6 14;"
        );
        btnVoltar.setCursor(javafx.scene.Cursor.HAND);
        btnVoltar.setOnAction(e -> {
            // Navigate back to home (or any route that should be the previous view)
            try {
                router.navigate("home");
            } catch (Exception ex) {
                // fallback: clear view
                this.getChildren().clear();
            }
        });

        Label titulo = new Label("Detalhes do Evento");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        header.getChildren().addAll(btnVoltar, space, titulo);

        // --- Cartão central  ---
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.TOP_LEFT);
        card.setStyle(
                "-fx-background-color: white; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 3);"
        );

        Label nome = new Label("Nome: " + evento.getNome());
        nome.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label dataInicio = new Label("Início: " + evento.getDataInicio().format(fmt));
        Label dataFim = new Label("Fim: " + evento.getDataFim().format(fmt));
        Label valor = new Label(String.format("Valor do ingresso: R$ %.2f", evento.getValorIngresso()));

        String categorias = evento.getCategorias() != null && !evento.getCategorias().isEmpty()
                ? evento.getCategorias().stream().map(Categoria::getNome).collect(Collectors.joining(", "))
                : "Nenhuma categoria";
        Label lblCategorias = new Label("Categorias: " + categorias);

        String organizadores = evento.getOrganizadores() != null && !evento.getOrganizadores().isEmpty()
                ? evento.getOrganizadores().stream().map(Organizador::getNome).collect(Collectors.joining(", "))
                : "Nenhum organizador";
        Label lblOrganizadores = new Label("Organizadores: " + organizadores);

        String participantes = evento.getParticipantes() != null && !evento.getParticipantes().isEmpty()
                ? evento.getParticipantes().stream().map(Usuario::getNome).collect(Collectors.joining(", "))
                : "Nenhum participante";
        Label lblParticipantes = new Label("Participantes: " + participantes);

        card.getChildren().addAll(nome, dataInicio, dataFim, valor, lblCategorias, lblOrganizadores, lblParticipantes);

        VBox center = new VBox(card);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20));

        setTop(header);
        setCenter(center);
    }

    public javafx.scene.Parent getView() {
        return this;
    }
}
