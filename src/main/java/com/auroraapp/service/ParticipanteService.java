package com.auroraapp.service;

import javax.naming.NameNotFoundException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.auroraapp.model.Participante;
import com.auroraapp.repository.ParticipanteRepositoryJDBC;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private final ParticipanteRepositoryJDBC participanteRepository;

    public boolean salvar(Participante usuario) {
        if(null != participanteRepository.buscarPorId(usuario.getId()))
        {
            throw new DuplicateKeyException("Organizador ja existe!");
        }

        try {
            participanteRepository.salvar(usuario);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deletar(Participante participante) throws NameNotFoundException {
        if(null == participanteRepository.buscarPorId(participante.getId()))
        {
            throw new NameNotFoundException("Organizador não encontrado");
        }

        try {
            participanteRepository.deletar(participante);
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

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