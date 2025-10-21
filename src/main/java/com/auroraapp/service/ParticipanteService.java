package com.auroraapp.service;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Participante;
import com.auroraapp.repository.ParticipanteRepositoryJDBC;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private final ParticipanteRepositoryJDBC participanteRepository;

    public void salvar(Participante usuario) {

    }

    public void deletar(Participante participante) {

    }

    public Participante buscarPorId(int id) {
        try {
            return participanteRepository.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}