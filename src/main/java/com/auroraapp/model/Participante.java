package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
public class Participante extends Usuario {
    @ManyToMany(mappedBy = "participantes")
    private LinkedList<Evento> eventosPartcipados;
}
