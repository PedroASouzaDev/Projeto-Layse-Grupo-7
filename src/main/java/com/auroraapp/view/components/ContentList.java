package com.auroraapp.view.components;

import com.auroraapp.model.Evento;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ContentList extends VBox {
    private final VBox list = new VBox(12); // agora campo, para atualizações dinâmicas

    public ContentList() {
        setPadding(new Insets(12, 8, 0, 8));
        setSpacing(12);

        // container with light gray background like mockup
        VBox container = new VBox(12);
        container.setStyle("""
            -fx-background-radius: 4;
            -fx-border-radius: 4;
        """);
        container.setPrefHeight(480);

        // inicialmente vazio; use setEvents(...) para popular
        ScrollPane sp = new ScrollPane(list);
        sp.setFitToWidth(true);
        sp.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        sp.setPrefViewportWidth(700);

        container.getChildren().add(sp);
        getChildren().add(container);
    }

    // aceita uma lista observável de eventos e atualiza automaticamente quando a lista muda
    public void setEvents(ObservableList<Evento> eventos) {
        updateFrom(eventos);
        eventos.addListener((ListChangeListener<Evento>) change -> updateFrom(eventos));
    }

    private void updateFrom(ObservableList<Evento> eventos) {
        list.getChildren().clear();
        for (Evento e : eventos) {
            list.getChildren().add(new EventRow(e));
        }
    }
}