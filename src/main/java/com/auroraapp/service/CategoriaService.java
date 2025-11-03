package com.auroraapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Categoria;
import com.auroraapp.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public  List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }
}
