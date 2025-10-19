package com;
import javax.swing.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.auroraapp.view.MainView;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuroraAppApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(AuroraAppApplication.class)
            .headless(false)
            .run(args);

        SwingUtilities.invokeLater(() -> {
            JFrame windowFrame = new MainView();
        });
    }
}