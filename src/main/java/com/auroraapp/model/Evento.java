package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valorIngresso;

    @ManyToMany(mappedBy = "evento_participante")
    private LinkedList<Usuario> participantes;

    @ManyToMany(mappedBy = "evento_organizador")
    private LinkedList<Organizador> organizadores;

    @ManyToMany(mappedBy = "evento_categoria")
    private LinkedList<Categoria> categorias;
}
