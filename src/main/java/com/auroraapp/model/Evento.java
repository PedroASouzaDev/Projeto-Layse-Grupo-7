package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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

    @ManyToMany
    @JoinTable(name = "evento_participante", 
    joinColumns = @JoinColumn(name = "evento_id"), 
    inverseJoinColumns = @JoinColumn(name = "participante_id"))
    private LinkedList<Usuario> participantes;

    @ManyToMany
    @JoinTable(name = "evento_organizador", 
    joinColumns = @JoinColumn(name = "evento_id"), 
    inverseJoinColumns = @JoinColumn(name = "organizador_id"))
    private LinkedList<Organizador> organizadores;

    @ManyToMany
    @JoinTable(name = "evento_categoria", 
    joinColumns = @JoinColumn(name = "evento_id"), 
    inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private LinkedList<Categoria> categorias;
}
