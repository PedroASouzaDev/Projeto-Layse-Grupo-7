package com.auroraapp.view.hooks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.auroraapp.model.Feedback;

import javafx.application.Platform;
import javafx.collections.ObservableList;

public class FeedbackHttpHook {
    private static final String BASE_URL = "http://localhost:8080/feedback";
    private ObservableList<Feedback> observableFeedbacks;

    private final HttpClient client;

    public FeedbackHttpHook(ObservableList<Feedback> observableFeedbacks) {
        this.client = HttpClient.newHttpClient();
        this.observableFeedbacks = observableFeedbacks;
    }

    public void fetchFeedbacksByEventoId(Long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/all/"+id))
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseBody -> {
                    
                    List<Feedback> feedbacks = JsonUtils.parseFeedbacks(responseBody);

                    Platform.runLater(() -> {
                        observableFeedbacks.clear();
                        observableFeedbacks.addAll(feedbacks);
                    });
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }
}
