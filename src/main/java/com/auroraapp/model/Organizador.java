package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class Organizador extends Usuario {
    @ManyToMany(mappedBy = "organizadores")
    private LinkedList<Evento> eventosOrganizados;
}
