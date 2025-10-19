package com;
import javax.swing.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuroraAppApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(AuroraAppApplication.class)
            .headless(false)
            .run(args);


        // run Swing on the EDT
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Aurora Swing UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // use EXIT_ON_CLOSE if you want JVM exit
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.add(new JLabel("Hello from Swing", SwingConstants.CENTER));
            frame.setVisible(true);
        });
    }
}