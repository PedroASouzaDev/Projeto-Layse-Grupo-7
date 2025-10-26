package com.auroraapp.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrganizadorRepositoryJDBC implements OrganizadorRepository{
    
    public void salvar(Usuario usuario) throws SQLException {
        
    }

    public void deletar(Usuario organizador) throws SQLException {

    }

    public Organizador buscarPorId(int id) {
        return null;
    }

    public void atualizarNome(int id, String nome) throws SQLException {
        
    }
}