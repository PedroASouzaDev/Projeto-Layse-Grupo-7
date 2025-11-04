package com.auroraapp.view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;

public class EventoForm extends VBox {

    private final TextField nomeEvento = new TextField();
    private final DatePicker dataEvento = new DatePicker();
    private final Label mensagemErro = new Label();

    public EventoForm() {
        super(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);

        Label titulo = new Label("Criar Evento");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        nomeEvento.setPromptText("Nome do evento");
        dataEvento.setPromptText("Data do evento");
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

        CheckBox tech = new CheckBox("Tecnologia");
        CheckBox saude = new CheckBox("Saúde");
        CheckBox educacao = new CheckBox("Educação");
        CheckBox entretenimento = new CheckBox("Entretenimento");

        popupContent.getChildren().addAll(tech, saude, educacao, entretenimento);
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

        popupContent.setOnMouseExited(e -> popup.hide());

        this.getChildren().addAll(titulo, nomeEvento, dataEvento, btnAbrir, mensagemErro, salvar);
    }

    private void validarECriarEvento() {
        String nome = nomeEvento.getText().trim();
        var data = dataEvento.getValue();

        if (nome.isEmpty()) {
            mensagemErro.setText("O nome do evento é obrigatório!");
        } else if (data == null) {
            mensagemErro.setText("A data do evento é obrigatória!");
        } else {
            mensagemErro.setText("");

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
    }
}
