package com.auroraapp.repository;

import java.sql.SQLException;

import com.auroraapp.model.Participante;
import com.auroraapp.model.Usuario;

interface ParticipanteRepository {

    public void salvar(Usuario usuario) throws SQLException;
    public void deletar(Usuario participante) throws SQLException;
    public Participante buscarPorId(int id) throws SQLException;

    public void atualizarNome(int id, String nome) throws SQLException;
}