package com.auroraapp.view.hooks;

import com.auroraapp.model.Evento;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EventoHttpHook {

    private static final String BASE_URL = "http://localhost:8080/evento";
    private ObservableList<Evento> observableEventos;

    private final HttpClient client;

    public EventoHttpHook(ObservableList<Evento> observableEventos) {
        this.client = HttpClient.newHttpClient();
        this.observableEventos = observableEventos;
    }

    public void fetchEventos() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/all"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    List<Evento> eventos = JsonUtils.parseEventos(responseBody);

                    Platform.runLater(() -> {
                        observableEventos.clear();
                        observableEventos.addAll(eventos);
                    });
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    public void createEvento(Evento evento) {
        String jsonBody = JsonUtils.toJson(evento);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    System.out.println("Evento created: " + responseBody);
                    Platform.runLater(() -> fetchEventos());
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }
}
