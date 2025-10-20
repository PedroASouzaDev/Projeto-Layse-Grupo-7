package com.auroraapp.model;

import java.util.LinkedList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Builder
public class Participante extends Usuario {
    private LinkedList<Evento> eventosPartcipados;
    private String matricula;
}
