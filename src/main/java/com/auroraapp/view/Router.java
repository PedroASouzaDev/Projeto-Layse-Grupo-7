package com.auroraapp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Router {
    private final StackPane root;
    private final Map<String, Supplier<Node>> routes = new HashMap<>();

    public Router(StackPane root) {
        this.root = root;
    }

    public void register(String name, Supplier<Node> supplier) {
        routes.put(name, supplier);
    }

    public void navigate(String name) {
        Supplier<Node> supplier = routes.get(name);
        if (supplier == null) {
            throw new IllegalArgumentException("No route registered: " + name);
        }
        Node node = supplier.get();
        root.getChildren().setAll(node);
    }

    // Navigate directly to a Node (useful for dynamic pages with parameters)
    public void navigateTo(Node node) {
        if (node == null) throw new IllegalArgumentException("Node cannot be null");
        root.getChildren().setAll(node);
    }

    public static Supplier<Node> fromFxml(String resourcePath) {
        return () -> {
            try {
                return FXMLLoader.load(Router.class.getResource(resourcePath));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load FXML: " + resourcePath, e);
            }
        };
    }
}