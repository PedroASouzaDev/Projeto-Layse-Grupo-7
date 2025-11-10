package com.auroraapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Evento;
import com.auroraapp.model.Feedback;
import com.auroraapp.model.Participante;
import com.auroraapp.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final EventoService eventoService;
    private final ParticipanteService participanteService;

    public Feedback salvar(Feedback feedback) {
        Evento evento = eventoService.buscarPorId(feedback.getEvento().getId());
        Participante participante = participanteService.buscarPorId(feedback.getParticipante().getId());
        
        feedback.setEvento(evento);
        feedback.setParticipante(participante);

        return feedbackRepository.save(feedback);
    }

    public void deletar(Long id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback buscarPorId(Long id) {
        return feedbackRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Feedback não encontrado com id: " + id));
    }

    public List<Feedback> buscarTodos() {
        return feedbackRepository.findAll();
    }
}
