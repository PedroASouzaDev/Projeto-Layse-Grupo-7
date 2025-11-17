package com.auroraapp.view.pages;

import java.util.List;

import com.auroraapp.model.Evento;
import com.auroraapp.model.Feedback;
import com.auroraapp.view.Router;
import com.auroraapp.view.components.HeaderBar;
import com.auroraapp.view.hooks.EventoHttpHook;
import com.auroraapp.view.hooks.FeedbackHttpHook;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Estatisticas extends BorderPane {
    private static final ObservableList<Evento> eventos = FXCollections.observableArrayList();
    private static final EventoHttpHook eventoHttp = new EventoHttpHook(eventos);

    public Estatisticas(Router router) {
        HeaderBar header = new HeaderBar(router);
        setTop(header);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        setCenter(grid);

        NumberAxis eixoX1 = new NumberAxis();
        eixoX1.setLabel("Eventos");
        NumberAxis eixoY1 = new NumberAxis();
        eixoY1.setLabel("Participantes");

        NumberAxis eixoX2 = new NumberAxis();
        eixoY1.setLabel("Participantes");
        NumberAxis eixoY2 = new NumberAxis();
        eixoY2.setLabel("Valor do Ingresso");

        NumberAxis eixoX3 = new NumberAxis();
        eixoX3.setLabel("Organizadores");
        NumberAxis eixoY3 = new NumberAxis();
        eixoY3.setLabel("Eventos");

        NumberAxis eixoX4 = new NumberAxis();
        eixoX4.setLabel("Eventos");
        NumberAxis eixoY4 = new NumberAxis();
        eixoX4.setLabel("Feedbacks");    
        
        NumberAxis eixoX5 = new NumberAxis();
        eixoX5.setLabel("Eventos");
        NumberAxis eixoY5 = new NumberAxis();
        eixoY5.setLabel("Média de notas");

        NumberAxis eixoX6 = new NumberAxis();
        eixoX6.setLabel("Eventos");
        NumberAxis eixoY6 = new NumberAxis();
        eixoY6.setLabel("% Comparecimento");

        //Qtd. Participantes por evento
        LineChart<Number, Number> participanteEvento = new LineChart<>(eixoX1, eixoY1);
        participanteEvento.setTitle("Participantes por Evento");
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();

        //Qtd. Participantes por valor do Ingresso
        LineChart<Number, Number> participanteIngresso = new LineChart<>(eixoX2, eixoY2);
        participanteIngresso.setTitle("Participantes por Ingresso");
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();

        //Qtd. Organizadores por evento
        LineChart<Number, Number> organizadorEvento = new LineChart<>(eixoX3, eixoY3);
        organizadorEvento.setTitle("Organizadores por Eventos");
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();

        //Qtd. Feedbacks por evento
        LineChart<Number, Number> feedbackEvento = new LineChart<>(eixoX4, eixoY4);
        feedbackEvento.setTitle("Feedbacks por eventos");
        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();

        //Média de notas por evento
        LineChart<Number, Number> medNotaEvento = new LineChart<>(eixoX5, eixoY5);
        feedbackEvento.setTitle("Média de notas por evento");
        XYChart.Series<Number, Number> series5 = new XYChart.Series<>();

        //Taxa de Comparecimento e Cancelamento por evento
        LineChart<Number, Number> comparecimentoEvento = new LineChart<>(eixoX6, eixoY6);
        comparecimentoEvento.setTitle("Taxa de Comparecimento e Cancelamento por Evento");
        XYChart.Series<Number, Number> series6 = new XYChart.Series<>();
        series6.setName("Comparecimento (%)");
        XYChart.Series<Number, Number> series7 = new XYChart.Series<>();
        series7.setName("Cancelamento (%)");

        eventoHttp.fetchEventos();

        ObservableList<Feedback> feedbacks = FXCollections.observableArrayList();
        FeedbackHttpHook feedbackHttp = new FeedbackHttpHook(feedbacks);

        eventos.addListener((ListChangeListener<Evento>) x -> {
            series1.getData().clear();
            series2.getData().clear();
            series3.getData().clear();
            series4.getData().clear();
            series5.getData().clear();
            series6.getData().clear();
            series7.getData().clear();
            for(Evento evento : eventos) {
                series1.getData().add(new XYChart.Data<>(evento.getId(), evento.getParticipantes().size()));
                series2.getData().add(new XYChart.Data<>(evento.getParticipantes().size(), evento.getValorIngresso()));
                series3.getData().add(new XYChart.Data<>(evento.getId(), evento.getOrganizadores().size()));

                feedbackHttp.fetchFeedbacksByEventoId(evento.getId());
                
                int total = evento.getParticipantes() != null ? evento.getParticipantes().size() : 0;
                int presentes = evento.getParticipantesPresentes() != null ? evento.getParticipantesPresentes().size() : 0;
                double taxa = total == 0 ? 0 : ((double) presentes / total) * 100;
                series6.getData().add(new XYChart.Data<>(evento.getId(), taxa));
            }
        }); 

        feedbacks.addListener((ListChangeListener<Feedback>) change -> {
            series4.getData().clear();
            series5.getData().clear();

            for (Evento evento : eventos) {
                List<Feedback> fList = feedbacks.stream()
                        .filter(f -> f.getEvento().getId().equals(evento.getId()))
                        .toList();

                int quantidade = fList.size();
                int media = quantidade == 0 ? 0 :
                        (int) fList.stream().mapToInt(Feedback::getNota).average().orElse(0);

                series4.getData().add(new XYChart.Data<>(evento.getId(), quantidade));
                series5.getData().add(new XYChart.Data<>(evento.getId(), media));
            }
        });

        participanteEvento.getData().add(series1);
        participanteIngresso.getData().add(series2);
        organizadorEvento.getData().add(series3);
        feedbackEvento.getData().add(series4);
        medNotaEvento.getData().add(series5);
        comparecimentoEvento.getData().add(series6);
        
        grid.add(participanteEvento, 0, 0);
        grid.add(participanteIngresso, 1, 0);
        grid.add(organizadorEvento, 2, 0);
        grid.add(feedbackEvento, 0, 1);
        grid.add(medNotaEvento, 1, 1);
        grid.add(comparecimentoEvento, 2, 1);
    }
}
