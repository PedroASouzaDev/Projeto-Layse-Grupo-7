package com.auroraapp.repository;

import java.sql.SQLException;

import com.auroraapp.model.Participante;
import com.auroraapp.model.Usuario;

interface ParticipanteRepository {

    //MISSING: Atualizar e Atualizar Parcialmente (Pensando nas requisições)
    public void salvar(Usuario usuario) throws SQLException;
    public void deletar(Usuario participante) throws SQLException;
    public Participante buscarPorId(int id) throws SQLException;
}