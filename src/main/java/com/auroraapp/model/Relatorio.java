package com.auroraapp.model;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private List<Aluno> participantes;
    private List<Organizador> organizadores;

    public Relatorio() {
        this.participantes = new ArrayList<>();
        this.organizadores = new ArrayList<>();
    }

    public void inscrever(Aluno usuario) {
        participantes.add(usuario);
    }

    public void inscrever(Organizador organizador) {
        organizadores.add(organizador);
    }

    public void info() {
        System.out.println("==== Relatório ====");

        System.out.println("\nParticipantes:");
        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante inscrito.");
        } else {
            for (Aluno a : participantes) {
                System.out.println("- " + a.getNome());
            }
        }

        System.out.println("\nOrganizadores:");
        if (organizadores.isEmpty()) {
            System.out.println("Nenhum organizador inscrito.");
        } else {
            for (Organizador o : organizadores) {
                System.out.println("- " + o.getNome());
            }
        }
    }
}