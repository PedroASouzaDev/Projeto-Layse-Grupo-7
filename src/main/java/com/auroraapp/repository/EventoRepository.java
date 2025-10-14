package com.auroraapp.repository;

import com.auroraapp.model.Evento;

interface EventoRepository {
    public void salvar(Evento evento);
    public Evento buscarPorId(int id);
    void deletar(Evento evento);
}
