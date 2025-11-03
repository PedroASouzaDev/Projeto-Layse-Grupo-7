package com.auroraapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Evento;
import com.auroraapp.repository.EventoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;

    public Evento salvar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deletar(Long id) {
        eventoRepository.deleteById(id);
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado com id: " + id));
    }

    public List<Evento> buscarTodos() {
        return eventoRepository.findAll();
    }
}
