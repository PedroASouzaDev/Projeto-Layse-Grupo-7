package com.auroraapp.repository;

import java.sql.SQLException;

import com.auroraapp.model.Evento;

interface EventoRepository {
    public void salvar(Evento evento) throws SQLException;
    public Evento buscarPorId(int id);
    public void deletar(Evento evento);
}
