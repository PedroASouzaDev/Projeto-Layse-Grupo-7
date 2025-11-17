package com.auroraapp.view.components;

import com.auroraapp.model.Evento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Grafico {
    private String titulo;
    private String xLabel;
    private String yLabel;
    private XYChart.Series<String, Number> series = new XYChart.Series<>();
    private ObservableList<Evento> eventos = FXCollections.observableArrayList();

    public Grafico() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xLabel);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabel);

        LineChart<String, Number> grafico = new LineChart<>(xAxis, yAxis);
        grafico.setTitle(titulo);

        for(Evento evento : eventos) {
            series.getData().add(new XYChart.Data<>(evento.getNome(), evento.getParticipantes().size()));
        }
        grafico.getData().add(series);
    }
}
