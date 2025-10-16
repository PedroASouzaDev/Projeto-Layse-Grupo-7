package com.auroraapp.repository;

import java.sql.SQLException;

import com.auroraapp.model.Evento;

interface EventoRepository {

    //MISSING: Atualizar e Atualizar Parcialmente (Pensando nas requisições)
    public void salvar(Evento evento) throws SQLException;
    public void deletar(Evento evento) throws SQLException;
    public Evento buscarPorId(int id) throws SQLException;
}
