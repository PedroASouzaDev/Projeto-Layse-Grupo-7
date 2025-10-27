package com.auroraapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public abstract class Usuario {
    @Id
    private Long id;
    private String nome;
}