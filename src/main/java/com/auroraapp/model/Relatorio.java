package com.auroraapp.model;

import lombok.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Relatorio {
    private Evento evento;

// Método para exibir as informações do relatório no console
    public void info() {
        if (evento == null) {
            System.out.println("Nenhum evento vinculado ao relatório.");
            return;
        }

        System.out.println("==== Relatório do Evento ====");
        System.out.println("Nome: " + evento.getNome());
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

        System.out.printf("\n Valor do ingresso: R$ %.2f%n", evento.getValorIngresso() != null ? evento.getValorIngresso() : 0.0);
        System.out.printf(" Total de participantes: %d%n", evento.getParticipantes() != null ? evento.getParticipantes().size() : 0);

        double total = calcularTotalArrecadado();
        System.out.printf(" Total arrecadado: R$ %.2f%n", total);
    }

//Metodo para gerar CSV
    public void gerarCSV(String caminhoArquivo) {
        if (evento == null) {
            System.err.println(" Nenhum evento definido para gerar o relatório.");
            return;
        }

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            writer.append("Relatório do Evento\n");
            writer.append("Nome,").append(evento.getNome()).append("\n");
            writer.append("Data Início,").append(formatarData(evento.getDataInicio())).append("\n");
            writer.append("Data Fim,").append(formatarData(evento.getDataFim())).append("\n\n");

            writer.append("Participantes\n");
            if (evento.getParticipantes() != null) {
                for (Usuario p : evento.getParticipantes()) {
                    writer.append(p.getNome()).append("\n");
                }
            }

            writer.append("\nOrganizadores\n");
            if (evento.getOrganizadores() != null) {
                for (Organizador o : evento.getOrganizadores()) {
                    writer.append(o.getNome()).append("\n");
                }
            }

            double total = calcularTotalArrecadado();
            writer.append("\nValor Ingresso,R$ ").append(String.format("%.2f", evento.getValorIngresso())).append("\n");
            writer.append("Total Arrecadado,R$ ").append(String.format("%.2f", total)).append("\n");

            System.out.println(" CSV gerado com sucesso em: " + caminhoArquivo);

        } catch (IOException e) {
            System.err.println(" Erro ao gerar CSV: " + e.getMessage());
        }
    }

// Método para cacular o valor ganho no evento
    private double calcularTotalArrecadado() {
        if (evento == null || evento.getParticipantes() == null || evento.getValorIngresso() == null) {
            return 0.0;
        }
        return evento.getParticipantes().size() * evento.getValorIngresso();
    }

    private String formatarData(java.time.LocalDate data) {
        return (data != null) ? data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "-";
    }
}
