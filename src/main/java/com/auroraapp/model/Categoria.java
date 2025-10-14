package com.auroraapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Categoria {
    private final int id;
    private final String nome;
}