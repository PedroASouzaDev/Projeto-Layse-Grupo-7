package com.auroraapp.model;

import lombok.Data;

@Data
abstract class Usuario {
    private int id;
    private String nome;
}