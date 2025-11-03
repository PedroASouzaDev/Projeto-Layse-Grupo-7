package com.auroraapp.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@DiscriminatorValue("ORG")
public class Organizador extends Usuario {
    private List<Evento> eventosOrganizados;
}
