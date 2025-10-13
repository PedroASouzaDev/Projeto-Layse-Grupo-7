package com.auroraapp.model;

import com.auroraapp.enums.Categoria;

import java.util.LinkedList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Evento {
    private String nome;
    private LinkedList<Usuario> participantes;
    private LinkedList<Organizador> organizadores;
    private Date dataInicio;
    private Date dataFim;
    private LinkedList<Categoria> categorias;
    private Double valorIngresso;  
}
