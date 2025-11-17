package com.auroraapp.view.pages;

import com.auroraapp.model.Evento;
import com.auroraapp.model.Feedback;
import com.auroraapp.model.Categoria;
import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;
import com.auroraapp.view.Router;
import com.auroraapp.view.components.EventRow;
import com.auroraapp.view.hooks.FeedbackHttpHook;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class EventoDetail extends BorderPane {
    private final VBox list = new VBox(10);
    private static final ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();
    private static final FeedbackHttpHook feedbackHttp = new FeedbackHttpHook(feedbacks);

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

        header.getChildren().addAll(btnVoltar);

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

        Label titulo = new Label("Detalhes do Evento");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Label feedbackTitulo = new Label("Feedbacks");
        feedbackTitulo.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

        VBox container = new VBox(20);
        container.setBackground(new Background(new BackgroundFill(Color.web("#e2e8f0"), CornerRadii.EMPTY, Insets.EMPTY)));
        container.setPadding(new Insets(20));
        container.getChildren().addAll(titulo, card, feedbackTitulo, list);

        ScrollPane sp = new ScrollPane(container);
        sp.setFitToWidth(true);
        sp.viewportBoundsProperty().addListener((obs, oldVal, newVal) -> {
            container.setPrefHeight(newVal.getHeight());
        });
        feedbackHttp.fetchFeedbacksByEventoId(evento.getId());

        setTop(header);
        setCenter(sp);
        setFeedbacks(feedbacks);
    }

    public void setFeedbacks(ObservableList<Feedback> feedbacks) {
        updateFrom(feedbacks);
        feedbacks.addListener((ListChangeListener<Feedback>) change -> updateFrom(feedbacks));
    }

    private void updateFrom(ObservableList<Feedback> feedbacks) {
        list.getChildren().clear();

        for (Feedback f : feedbacks) {
            list.getChildren().add(criarCardFeedback(f));
        }
    }

    public VBox criarCardFeedback(Feedback feedback) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.TOP_LEFT);
        card.setStyle(
                "-fx-background-color: white; " +
                "-fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 3);"
        );
        Label nota = new Label("Nota: " + feedback.getNota());
        Label comentario = new Label("Comentário: " + feedback.getComentario());
        card.getChildren().addAll(nota, comentario);
        return card;
    }

    public javafx.scene.Parent getView() {
        return this;
    }
}
