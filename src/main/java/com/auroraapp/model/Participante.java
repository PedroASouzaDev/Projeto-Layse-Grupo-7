package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class Participante extends Usuario {
    @ManyToMany(mappedBy = "participantes")
    private LinkedList<Evento> eventosPartcipados;
}
