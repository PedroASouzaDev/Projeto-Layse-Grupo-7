package com.auroraapp.repository;

import java.sql.SQLException;

import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;

interface OrganizadorRepository {

    //MISSING: Atualizar e Atualizar Parcialmente (Pensando nas requisições)
    public void salvar(Usuario usuario) throws SQLException;
    public void deletar(Usuario organizador) throws SQLException;
    public Organizador buscarPorId(int id) throws SQLException;
}