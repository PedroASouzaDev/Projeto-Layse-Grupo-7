package com.auroraapp.view.pages;

import com.auroraapp.model.Evento;
import com.auroraapp.view.Router;
import com.auroraapp.view.components.HeaderBar;
import com.auroraapp.view.hooks.EventoHttpHook;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Estatisticas extends BorderPane {
    private static final ObservableList<Evento> eventos = FXCollections.observableArrayList();
    private static final EventoHttpHook eventoHttp = new EventoHttpHook(eventos);

    public Estatisticas(Router router) {
        HeaderBar header = new HeaderBar();
        setTop(header);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        setCenter(grid);

        eventoHttp.fetchEventos();

        //Qtd. Participantes por evento
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Eventos");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Participantes");

        LineChart<String, Number> participanteEvento = new LineChart<>(xAxis, yAxis);
        participanteEvento.setTitle("Participantes por Evento");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        eventos.addListener((ListChangeListener<Evento>) change -> {
            series.getData().clear();
            for(Evento evento : eventos) {
                series.getData().add(new XYChart.Data<>(evento.getNome(), evento.getParticipantes().size()));
            }
        });
        participanteEvento.getData().add(series);
        grid.add(participanteEvento, 0, 0);

        //Qtd. Participantes por valor do Ingresso

        //Qtd. Organizadores por evento

        //Qtd. Feedbacks por evento

        //Média de notas por evento
    }
}
