package com.auroraapp.service;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Evento;
import com.auroraapp.repository.EventoRepositoryJDBC;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepositoryJDBC eventoRepository;

    public void salvar(Evento evento) {}

    public void deletar(Evento evento) {}

    // ONLY FOR TESTING ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    public Evento buscarPorId(int id) {
        try {
            return eventoRepository.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
