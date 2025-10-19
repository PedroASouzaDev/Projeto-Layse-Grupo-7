package com.auroraapp.view;

import java.awt.CardLayout;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

import com.auroraapp.model.Usuario;

import com.auroraapp.view.components.Teste;

public class ViewController {
    public static CardLayout layout;
    public static JPanel frameContainer;
    private static final Set<String> telasAdicionadas = new HashSet<>();

    public static void mudarTela(String nomeTela, Usuario usuario) {
        switch (nomeTela) {
            // EXEMPLO
            // case "NomeMenu":
            // if (!telasAdicionadas.contains("NomeMenu")) {
            // NomeMenu menu = new NomeMenu(...);
            // frameContainer.add(menu, "NomeMenu");
            // telasAdicionadas.add("NomeMenu");
            // }
            // break;
        }

        layout.show(frameContainer, nomeTela);
    }

    // Telas sem usuários, ex.: login
    public static void mudarTela(String nomeTela) {
        if (!telasAdicionadas.contains(nomeTela)) {
            switch (nomeTela) {
                // case "Login":
                //     Login login = new Login();
                //     frameContainer.add(login, "Login");
                //     break;
            
                case "Teste":
                    Teste teste = new Teste();
                    frameContainer.add(teste, "Teste");
                    break;
            }
            telasAdicionadas.add(nomeTela);
        }

        layout.show(frameContainer, nomeTela);
    }
}
