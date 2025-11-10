package com.auroraapp.view.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.auroraapp.model.Categoria;
import com.auroraapp.model.Evento;
import com.auroraapp.view.hooks.CategoriaHttpHook;
import com.auroraapp.view.hooks.EventoHttpHook;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class EventoForm extends VBox {

    private final TextField nomeEvento = new TextField();
    private final DatePicker dataEvento = new DatePicker();
    private final DatePicker dataEventoFim = new DatePicker();
    private final TextField inscricaoEvento = new TextField();
    private final Label mensagemErro = new Label();
    private final List<CheckBox> checked = new ArrayList<>();
    private final ObservableList<Categoria> categorias = javafx.collections.FXCollections.observableArrayList();
    private final CategoriaHttpHook categoriaHttp = new CategoriaHttpHook(categorias);
    private final EventoHttpHook eventoHttp;

    public EventoForm(EventoHttpHook eventoHttp) {
        super(15);
        this.eventoHttp = eventoHttp;

        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);

        Label titulo = new Label("Criar Evento");
        titulo.setStyle("""
            -fx-font-size: 20px;
            -fx-font-weight: bold;
        """);

        nomeEvento.setPromptText("Nome do evento");
        inscricaoEvento.setPromptText("Valor da Inscrição");
        dataEvento.setPromptText("Data de início");
        dataEventoFim.setPromptText("Data de término");
        mensagemErro.setStyle("-fx-text-fill: red;");

        // Botão de salvar
        Button salvar = new Button("Salvar Evento");
        salvar.setStyle("""
            -fx-background-color: #2196f3;
            -fx-text-fill: white;
            -fx-font-weight: bold;
        """);
        salvar.setOnMouseEntered(e -> salvar.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-weight: bold;"));
        salvar.setOnMouseExited(e -> salvar.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-font-weight: bold;"));
        salvar.setOnAction(e -> validarECriarEvento());

        // Botão de seleção de categorias
        Button btnAbrir = new Button("Selecionar categorias");

        // ContextMenu substituindo o Popup
        ContextMenu menuCategorias = new ContextMenu();
        menuCategorias.setAutoHide(true);

        categoriaHttp.fetchCategorias();

        // Atualiza o menu quando as categorias são carregadas
        categorias.addListener((javafx.collections.ListChangeListener<Categoria>) change -> {
            menuCategorias.getItems().clear();
            checked.clear();

            for (Categoria cat : categorias) {
                CheckBox checkBox = new CheckBox(cat.getNome());
                CustomMenuItem item = new CustomMenuItem(checkBox);
                item.setHideOnClick(false); // mantém o menu aberto ao clicar
                menuCategorias.getItems().add(item);
                checked.add(checkBox);
            }
        });

        // Abre o menu
        btnAbrir.setOnAction(e -> {
            if (menuCategorias.isShowing()) {
                menuCategorias.hide();
            } else {
                menuCategorias.show(btnAbrir, javafx.geometry.Side.BOTTOM, 0, 0);
            }
        });

        this.getChildren().addAll(titulo, nomeEvento, inscricaoEvento, dataEvento, dataEventoFim, btnAbrir, mensagemErro, salvar);
    }

    private void validarECriarEvento() {
        String nome = nomeEvento.getText().trim();
        String inscricaoTexto = inscricaoEvento.getText().trim();
        Double inscricao = null;
        if (!inscricaoTexto.isEmpty()) {
            try {
                inscricao = Double.parseDouble(inscricaoTexto);
            } catch (NumberFormatException e) {
                mensagemErro.setText("O valor da inscrição deve ser numérico!");
                return;
            }
        }

        var data = dataEvento.getValue();
        var dataFim = dataEventoFim.getValue();
        List<Categoria> listCategorias = new ArrayList<>();

        if (nome.isEmpty()) {
            mensagemErro.setText("O nome do evento é obrigatório!");
        } else if (inscricao == null) {
            mensagemErro.setText("O valor da inscrição é obrigatório!");
        } else if (data == null) {
            mensagemErro.setText("A data de início do evento é obrigatória!");
        } else if (dataFim == null) {
            mensagemErro.setText("A data de término do evento é obrigatória!");
        } else {
            mensagemErro.setText("");

            List<String> categoriasSelecionadas = checked.stream()
                    .filter(CheckBox::isSelected)
                    .map(CheckBox::getText)
                    .collect(Collectors.toList());

            for (String cat : categoriasSelecionadas) {
                for (Categoria c : categorias) {
                    if (c.getNome().equals(cat)) {
                        listCategorias.add(c);
                        break;
                    }
                }
            }

            Evento evento = Evento.builder()
                    .nome(nome)
                    .valorIngresso(inscricao)
                    .dataInicio(data)
                    .dataFim(dataFim)
                    .categorias(listCategorias)
                    .build();

            eventoHttp.createEvento(evento);

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Evento criado");
            alerta.setHeaderText(null);
            alerta.setContentText("Evento " + nome + " criado com sucesso!");
            alerta.showAndWait();

            limparCampos();
            this.getScene().getWindow().hide();
        }
    }

    private void limparCampos() {
        nomeEvento.clear();
        inscricaoEvento.clear();
        dataEvento.setValue(null);
        dataEventoFim.setValue(null);
        checked.forEach(cb -> cb.setSelected(false));
    }
}
