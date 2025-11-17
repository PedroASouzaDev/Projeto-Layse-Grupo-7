package com.auroraapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import com.auroraapp.view.MainView;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuroraAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraAppApplication.class, args);

        // Thread do JavaFX
        new Thread(() -> Application.launch(JavaFXApp.class)).start();
    }

    public static class JavaFXApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            MainView mainView = new MainView();
            mainView.start(primaryStage);
        }

        @Override
        public void stop() {
            Platform.exit();
        }
    }
}
