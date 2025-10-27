package com.auroraapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auroraapp.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
