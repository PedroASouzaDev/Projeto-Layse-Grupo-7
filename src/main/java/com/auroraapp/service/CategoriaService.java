package com.auroraapp.service;

import com.auroraapp.model.Categoria;
import com.auroraapp.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }
}
