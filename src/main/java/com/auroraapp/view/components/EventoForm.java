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
import javafx.stage.Popup;
import javafx.stage.Window;

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
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        nomeEvento.setPromptText("Nome do evento");
        inscricaoEvento.setPromptText("Valor da Inscrição");
        dataEvento.setPromptText("Data de início");
        dataEventoFim.setPromptText("Data de término");
        mensagemErro.setStyle("-fx-text-fill: red;");

        Button salvar = new Button("Salvar Evento");
        salvar.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-font-weight: bold;");
        salvar.setOnMouseEntered(e -> salvar.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-weight: bold;"));
        salvar.setOnMouseExited(e -> salvar.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white; -fx-font-weight: bold;"));
        salvar.setOnAction(e -> validarECriarEvento());

        Button btnAbrir = new Button("Selecionar categorias");

        Popup popup = new Popup();
        VBox popupContent = new VBox(10);
        popupContent.setStyle("""
            -fx-background-color: white;
            -fx-border-color: gray;
            -fx-padding: 10;
            -fx-background-radius: 6;
            -fx-border-radius: 6;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 6, 0, 0, 1);
        """);

        categoriaHttp.fetchCategorias();
        categorias.addListener((javafx.collections.ListChangeListener<Categoria>) change -> {
            popupContent.getChildren().clear();
            checked.clear();

            for (Categoria cat : categorias) {
                CheckBox checkBox = new CheckBox(cat.getNome());
                checked.add(checkBox);
            }

            popupContent.getChildren().addAll(checked);
        });

        popupContent.getChildren().addAll(checked);
        popup.setAutoHide(true);
        popup.getContent().add(popupContent);

        btnAbrir.setOnAction(e -> {
            if (popup.isShowing()) {
                popup.hide();
            } else {
                Window window = btnAbrir.getScene().getWindow();
                var screenBounds = btnAbrir.localToScreen(btnAbrir.getBoundsInLocal());
                popup.show(window, screenBounds.getMinX(), screenBounds.getMaxY());
            }
        });

        popup.setOnHidden(e -> {
            List<String> selectedCategories = checked.stream()
                    .filter(CheckBox::isSelected)
                    .map(CheckBox::getText)
                    .collect(Collectors.toList());
            System.out.println("Categorias selecionadas: " + selectedCategories);
        });

        this.getChildren().addAll(titulo, nomeEvento, inscricaoEvento, dataEvento, dataEventoFim, btnAbrir, mensagemErro, salvar);
    }

    private void validarECriarEvento() {
        String nome = nomeEvento.getText().trim();
        Double inscricao = inscricaoEvento.getText().isEmpty() ? null : Double.parseDouble(inscricaoEvento.getText().trim());
        var data = dataEvento.getValue();
        var dataFim = dataEvento.getValue();
        List<Categoria> listCategorias = new ArrayList<>();

        if (nome.isEmpty()) {
            mensagemErro.setText("O nome do evento é obrigatório!");
        }else if (inscricao.equals(null)) {
            mensagemErro.setText("O valor da inscrição é obrigatório!");
        } else if (data == null) {
            mensagemErro.setText("A data de inicio do evento é obrigatória!");
        } else if (dataFim == null) {
            mensagemErro.setText("A data de termino do evento é obrigatória!");
        } else {
            mensagemErro.setText("");

            List<String> categoriasSelecionadas = checked.stream()
                    .filter(CheckBox::isSelected)
                    .map(CheckBox::getText)
                    .collect(Collectors.toList());

            System.out.println("Criando evento: " + nome + " | Categorias: " + categoriasSelecionadas);

            for(String cat : categoriasSelecionadas)
            {
                for(Categoria c : categorias)
                {
                    if(c.getNome().equals(cat))
                    {
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
        }
    }

    private void limparCampos() {
        nomeEvento.clear();
        dataEvento.setValue(null);
        dataEventoFim.setValue(null);
        checked.forEach(cb -> cb.setSelected(false));
    }
}
