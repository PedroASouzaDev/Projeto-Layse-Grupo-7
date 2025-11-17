package com.auroraapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auroraapp.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
