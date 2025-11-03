package com.auroraapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auroraapp.model.Categoria;
import com.auroraapp.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CateoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/all")
    public List<Categoria> getAllCategorias() {
        System.out.println("Fetching all categorias: 200 OK");
        return categoriaService.buscarTodos();
    }
}
