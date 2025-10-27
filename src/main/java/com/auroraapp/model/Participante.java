package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@DiscriminatorValue("PAR")
public class Participante extends Usuario {
    @ManyToMany(mappedBy = "participantes")
    private LinkedList<Evento> eventosPartcipados;
}
