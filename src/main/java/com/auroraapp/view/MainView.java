package com.auroraapp.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainView extends JFrame {
    public MainView(){
        ViewController.layout = new CardLayout();
        ViewController.frameContainer = new JPanel(ViewController.layout);

        int larguraFrame = 1200;
        int alturaFrame = 600;
        JFrame frame = new JFrame("Relatórios e Estatisticas");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(ViewController.frameContainer);
        frame.setSize(larguraFrame, alturaFrame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ViewController.mudarTela("Teste");
    }
}
