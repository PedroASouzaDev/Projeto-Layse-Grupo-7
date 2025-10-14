package com.auroraapp.repository;

import com.auroraapp.model.Categoria;
import com.auroraapp.model.Evento;
import com.auroraapp.model.Usuario;
import com.auroraapp.model.Organizador;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

@Repository
@RequiredArgsConstructor
public class EventoRepositoryJDBC implements EventoRepository {

    private final DataSource dataSource;

    public void salvar(Evento evento) throws SQLException {
        String sql = "INSERT INTO evento (nome, dataInicio, dataFim, valorIngresso) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                ps.setString(1, evento.getNome());
                ps.setDate(2, java.sql.Date.valueOf(evento.getDataInicio()));
                ps.setDate(3, java.sql.Date.valueOf(evento.getDataFim()));
                ps.setDouble(4, evento.getValorIngresso());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int eventoId = rs.getInt(1);
                    evento.setId(eventoId);

                    String sqlParticipantes = "INSERT INTO evento_usuario (evento_id, usuario_id) VALUES (?, ?)";
                    try (PreparedStatement psParticipantes = conn.prepareStatement(sqlParticipantes)) {
                        for (Usuario usuario : evento.getParticipantes()) {
                            psParticipantes.setInt(1, evento.getId());
                            psParticipantes.setInt(2, usuario.getId());
                            psParticipantes.addBatch();
                        }
                        psParticipantes.executeBatch();
                    }

                    String sqlOrganizadores = "INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (?, ?)";
                    try (PreparedStatement psOrganizadores = conn.prepareStatement(sqlOrganizadores)) {
                        for(Organizador organizador : evento.getOrganizadores()) {
                            psOrganizadores.setInt(1, evento.getId());
                            psOrganizadores.setInt(2, organizador.getId());
                            psOrganizadores.addBatch();
                        }
                        psOrganizadores.executeBatch();
                    }

                    String sqlCategoria = "INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (?, ?)";
                    try (PreparedStatement psCategoria = conn.prepareStatement(sqlCategoria)) {
                        for(Categoria categoria : evento.getCategorias()) {
                            psCategoria.setInt(1, evento.getId());
                            psCategoria.setInt(2, categoria.getId());
                            psCategoria.addBatch();
                        }
                        psCategoria.executeBatch();
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
