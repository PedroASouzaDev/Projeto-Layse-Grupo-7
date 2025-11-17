package com.auroraapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Participante;
import com.auroraapp.repository.ParticipanteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository;

    public Participante salvar(Participante participante) {
        return participanteRepository.save(participante);
    }

    public void deletar(Long id) {
        participanteRepository.deleteById(id);
    }

    public Participante buscarPorId(Long id) {
        return participanteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Participante não encontrado com id: " + id));
    }

    public List<Participante> buscarTodos() {
        return participanteRepository.findAll();
    }
}
