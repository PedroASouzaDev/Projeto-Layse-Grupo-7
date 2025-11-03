package com.auroraapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auroraapp.model.Evento;
import com.auroraapp.repository.EventoRepository;
import com.auroraapp.service.EventoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evento")
public class EventoController {
    private final EventoService eventoService;

    @GetMapping("/all")
    public List<Evento> getAll() {
        System.out.println("Fetching all eventos");
        return eventoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Evento> getEventoById(@PathVariable Long id) {
        System.out.println("Fetching evento with ID: " + id + " - 200 OK");
        return eventoService.buscarPorId(id);
    }
    
    @PostMapping
    public Evento salvar(@RequestBody Evento evento) {
        System.out.println("Creating new evento - 201 Created");
        return eventoService.salvar(evento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        System.out.println("Deleting evento with ID: " + id + " - 204 No Content");
        eventoService.deletar(id);
    }
}