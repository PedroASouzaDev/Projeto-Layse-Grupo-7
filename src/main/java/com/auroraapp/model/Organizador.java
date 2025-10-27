package com.auroraapp.model;

import java.util.LinkedList;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@DiscriminatorValue("ORG")
public class Organizador extends Usuario {
    private LinkedList<Evento> eventosOrganizados;
}
