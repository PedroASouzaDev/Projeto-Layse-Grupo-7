package com.auroraapp.view.components;

import com.auroraapp.model.Evento;
import com.auroraapp.model.Categoria;
import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class EventoDetail {

    private final Evento evento;
    private BorderPane root;

    public EventoDetail(Evento evento) {
        this.evento = evento;
        // Formato de data
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Fundo principal (mesma cor da tela anterior)
        root = new BorderPane();
        root.setStyle("-fx-background-color: #e8edf5;"); // azul-claro suave

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

        Label titulo = new Label("Detalhes do Evento");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Region space = new Region();
        HBox.setHgrow(space, Priority.ALWAYS);

        header.getChildren().addAll(btnVoltar, space, titulo);

        // --- Cartão central com os dados ---
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

        // Centralizar o cartão
        VBox center = new VBox(card);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20));

        root.setTop(header);
        root.setCenter(center);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Detalhes do Evento");

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();

        // --- Ação do botão voltar ---
        Button btnVoltar = (Button) ((HBox) root.getTop()).getChildren().get(0);
        btnVoltar.setOnAction(e -> stage.close());
    }

    public javafx.scene.Parent getView() {
        return root;
    }
}
