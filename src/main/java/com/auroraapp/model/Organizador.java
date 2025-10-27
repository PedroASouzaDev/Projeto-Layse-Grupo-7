package com.auroraapp.model;

import java.util.LinkedList;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Organizador extends Usuario {
    private LinkedList<Evento> eventosOrganizados;
}
