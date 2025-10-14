package com.auroraapp.repository;

import com.auroraapp.model.Categoria;
import com.auroraapp.model.Evento;
import com.auroraapp.model.Usuario;
import com.auroraapp.model.Organizador;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

@Repository
@RequiredArgsConstructor
public class EventoRepositoryJDBC implements EventoRepository {

    private final DataSource dataSource;

    private void inserirRelacionamentos(Connection conn, String sql, int eventoId, List<Integer> ids) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Integer idRelacionado : ids) {
                ps.setInt(1, eventoId);
                ps.setInt(2, idRelacionado);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public void salvar(Evento evento) throws SQLException {
        String sqlEvento = "INSERT INTO evento (nome, dataInicio, dataFim, valorIngresso) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); 
            
            try (PreparedStatement ps = conn.prepareStatement(sqlEvento, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, evento.getNome());
                ps.setDate(2, java.sql.Date.valueOf(evento.getDataInicio()));
                ps.setDate(3, java.sql.Date.valueOf(evento.getDataFim()));
                ps.setDouble(4, evento.getValorIngresso());
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int eventoId = rs.getInt(1);
                    evento.setId(eventoId);
                    
                    if (evento.getParticipantes() != null && !evento.getParticipantes().isEmpty()) {
                        inserirRelacionamentos(
                            conn,
                            "INSERT INTO evento_usuario (evento_id, usuario_id) VALUES (?, ?)",
                            eventoId,
                            evento.getParticipantes().stream().map(Usuario::getId).toList()
                        );
                    }

                    if (evento.getOrganizadores() != null && !evento.getOrganizadores().isEmpty()) {
                        inserirRelacionamentos(
                            conn,
                            "INSERT INTO evento_organizador (evento_id, organizador_id) VALUES (?, ?)",
                            eventoId,
                            evento.getOrganizadores().stream().map(Organizador::getId).toList()
                        );
                    }

                    if (evento.getCategorias() != null && !evento.getCategorias().isEmpty()) {
                        inserirRelacionamentos(
                            conn,
                            "INSERT INTO evento_categoria (evento_id, categoria_id) VALUES (?, ?)",
                            eventoId,
                            evento.getCategorias().stream().map(Categoria::getId).toList()
                        );
                    }
                }
                conn.commit();

            } catch (SQLException e) {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
                throw e;
            }
        }
    }

    public Evento buscarPorId(int id) {};
    public void deletar(Evento evento) {}
}
