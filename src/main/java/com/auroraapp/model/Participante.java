package com.auroraapp.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private List<Evento> eventosPartcipados;
}
