package com.auroraapp.model;

import lombok.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelatoriosCustom {

    private List<Evento> eventosFiltrados;

    public void info() {
        if (eventosFiltrados == null || eventosFiltrados.isEmpty()) {
            System.out.println("Nenhum evento filtrado para gerar relatório.");
            return;
        }

        System.out.println("==== RELATÓRIO CUSTOMIZADO ====");

        for (Evento evento : eventosFiltrados) {

            System.out.println("\n------------------------------------------");
            System.out.println("Evento: " + evento.getNome());
            System.out.println("Data de início: " + formatarData(evento.getDataInicio()));
            System.out.println("Data de término: " + formatarData(evento.getDataFim()));

            System.out.println("\nParticipantes:");
            if (evento.getParticipantes() == null || evento.getParticipantes().isEmpty()) {
                System.out.println("Nenhum participante inscrito.");
            } else {
                evento.getParticipantes().forEach(p -> System.out.println("- " + p.getNome()));
            }

            System.out.println("\nOrganizadores:");
            if (evento.getOrganizadores() == null || evento.getOrganizadores().isEmpty()) {
                System.out.println("Nenhum organizador cadastrado.");
            } else {
                evento.getOrganizadores().forEach(o -> System.out.println("- " + o.getNome()));
            }

            int totalParticipantes = evento.getParticipantes() != null ? evento.getParticipantes().size() : 0;
            double valorIngresso = evento.getValorIngresso() != null ? evento.getValorIngresso() : 0.0;
            double totalArrecadado = totalParticipantes * valorIngresso;

            System.out.printf("\nValor do ingresso: R$ %.2f%n", valorIngresso);
            System.out.printf("Total de participantes: %d%n", totalParticipantes);
            System.out.printf("Total arrecadado: R$ %.2f%n", totalArrecadado);
        }
    }

    public void gerarCSV(String caminhoArquivo) {

        if (eventosFiltrados == null || eventosFiltrados.isEmpty()) {
            System.err.println("Nenhum evento filtrado para gerar relatório.");
            return;
        }

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {

            writer.append("RELATÓRIO CUSTOMIZADO\n\n");

            for (Evento evento : eventosFiltrados) {

                writer.append("Evento,").append(evento.getNome()).append("\n");
                writer.append("Data Início,").append(formatarData(evento.getDataInicio())).append("\n");
                writer.append("Data Fim,").append(formatarData(evento.getDataFim())).append("\n\n");

                writer.append("Participantes\n");
                if (evento.getParticipantes() != null) {
                    evento.getParticipantes().forEach(p -> {
                        try {
                            writer.append(p.getNome()).append("\n");
                        } catch (IOException e) {}
                    });
                }

                writer.append("\nOrganizadores\n");
                if (evento.getOrganizadores() != null) {
                    evento.getOrganizadores().forEach(o -> {
                        try {
                            writer.append(o.getNome()).append("\n");
                        } catch (IOException e) {}
                    });
                }

                int totalParticipantes = evento.getParticipantes() != null ? evento.getParticipantes().size() : 0;
                double valorIngresso = evento.getValorIngresso() != null ? evento.getValorIngresso() : 0.0;
                double totalArrecadado = totalParticipantes * valorIngresso;

                writer.append("\nValor Ingresso,R$ ").append(String.format("%.2f", valorIngresso)).append("\n");
                writer.append("Total Arrecadado,R$ ").append(String.format("%.2f", totalArrecadado)).append("\n");
                writer.append("\n---------------------------------------------\n\n");
            }

            System.out.println("CSV gerado com sucesso em: " + caminhoArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao gerar CSV: " + e.getMessage());
        }
    }

    private String formatarData(java.time.LocalDate data) {
        return (data != null) ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "-";
    }
}
