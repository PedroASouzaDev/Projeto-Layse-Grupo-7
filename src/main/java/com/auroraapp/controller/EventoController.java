package com.auroraapp.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.auroraapp.service.EventoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/evento")
public class EventoController {
    private final EventoService eventoService;

}