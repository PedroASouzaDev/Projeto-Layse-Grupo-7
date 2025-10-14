package com.auroraapp.model;

import lombok.Data;

@Data
public abstract class Usuario {
    private int id;
    private String email;
    private String senha;
    private String nome;
}