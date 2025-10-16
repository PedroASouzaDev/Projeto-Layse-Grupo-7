package com.auroraapp.model;

import java.util.LinkedList;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evento {
    private int id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valorIngresso;
    private LinkedList<Usuario> participantes;
    private LinkedList<Organizador> organizadores;
    private LinkedList<Categoria> categorias;
}
