package com.auroraapp.view.hooks;

import com.auroraapp.model.Evento;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EventoHttpHook {

    private static final String BASE_URL = "http://localhost:8080/evento/all";

    private final HttpClient client;

    public EventoHttpHook() {
        this.client = HttpClient.newHttpClient();
    }

    // -------------------------------
    // 1️⃣ Método para ListView<String>
    // -------------------------------
    public void fetchEventos(ListView<String> listView) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    List<Evento> eventos = JsonUtils.parseEventos(responseBody);

                    Platform.runLater(() -> {
                        listView.getItems().clear();
                        for (Evento e : eventos) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(e.getNome())
                              .append(" (")
                              .append(e.getDataInicio())
                              .append(" - ")
                              .append(e.getDataFim())
                              .append(") - R$")
                              .append(e.getValorIngresso())
                              .append("\n");

                            if (e.getParticipantes() != null && !e.getParticipantes().isEmpty()) {
                                sb.append("Participantes: ");
                                e.getParticipantes().forEach(p -> sb.append(p.getNome()).append(", "));
                                sb.setLength(sb.length() - 2);
                                sb.append("\n");
                            }

                            if (e.getOrganizadores() != null && !e.getOrganizadores().isEmpty()) {
                                sb.append("Organizadores: ");
                                e.getOrganizadores().forEach(o -> sb.append(o.getNome()).append(", "));
                                sb.setLength(sb.length() - 2);
                                sb.append("\n");
                            }

                            if (e.getCategorias() != null && !e.getCategorias().isEmpty()) {
                                sb.append("Categorias: ");
                                e.getCategorias().forEach(c -> sb.append(c.getNome()).append(", "));
                                sb.setLength(sb.length() - 2);
                                sb.append("\n");
                            }

                            listView.getItems().add(sb.toString());
                        }
                    });
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    // -----------------------------------------
    // 2️⃣ Método para ObservableList<Evento>
    // -----------------------------------------
    public void fetchEventos(ObservableList<Evento> observableEventos) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
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
}
