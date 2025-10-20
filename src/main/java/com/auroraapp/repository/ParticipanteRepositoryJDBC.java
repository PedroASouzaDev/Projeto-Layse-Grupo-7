package com.auroraapp.repository;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.auroraapp.model.Participante;
import com.auroraapp.model.Usuario;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ParticipanteRepositoryJDBC implements ParticipanteRepository{
    
    public void salvar(Usuario usuario) throws SQLException {
        
    }

    public void deletar(Usuario participante) throws SQLException {

    }

    public Participante buscarPorId(int id) throws SQLException {
        try {
            
            return null;
        } catch (Exception e) {
            
            return null;
        }
    }
}