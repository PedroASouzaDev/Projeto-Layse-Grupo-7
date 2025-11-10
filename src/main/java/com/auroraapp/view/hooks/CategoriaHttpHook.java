package com.auroraapp.view.hooks;

import com.auroraapp.model.Categoria;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CategoriaHttpHook {

    private static final String BASE_URL = "http://localhost:8080/categoria";
    private ObservableList<Categoria> observableCategorias;

    private final HttpClient client;

    public CategoriaHttpHook(ObservableList<Categoria> observableCategorias) {
        this.client = HttpClient.newHttpClient();
        this.observableCategorias = observableCategorias;
    }

    public void fetchCategorias() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/all"))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    List<Categoria> categorias = JsonUtils.parseCategoria(responseBody);

                    Platform.runLater(() -> {
                        observableCategorias.clear();
                        observableCategorias.addAll(categorias);
                    });
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    // public void createEvento(Evento evento) {
    //     String jsonBody = JsonUtils.toJson(evento);

    //     HttpRequest request = HttpRequest.newBuilder()
    //             .uri(URI.create(BASE_URL))
    //             .header("Content-Type", "application/json")
    //             .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
    //             .build();

    //     client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    //             .thenApply(HttpResponse::body)
    //             .thenAccept(responseBody -> {
    //                 System.out.println("Evento created: " + responseBody);
    //                 Platform.runLater(() -> fetchEventos());
    //             })
    //             .exceptionally(ex -> {
    //                 ex.printStackTrace();
    //                 return null;
    //             });
    // }
}

