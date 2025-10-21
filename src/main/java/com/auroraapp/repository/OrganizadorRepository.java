package com.auroraapp.repository;

import java.sql.SQLException;

import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;

interface OrganizadorRepository {

    public void salvar(Usuario usuario) throws SQLException;
    public void deletar(Usuario organizador) throws SQLException;
    public Organizador buscarPorId(int id) throws SQLException;

    public void atualizarNome(int id, String nome) throws SQLException;
}