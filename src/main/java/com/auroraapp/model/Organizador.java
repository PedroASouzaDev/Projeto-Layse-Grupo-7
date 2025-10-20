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
public class Organizador extends Usuario {
    private LinkedList<Evento> eventosOrganizados;
}
