package com.auroraapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.auroraapp.model.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    
}