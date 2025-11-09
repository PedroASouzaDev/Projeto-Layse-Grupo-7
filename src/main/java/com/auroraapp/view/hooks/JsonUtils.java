package com.auroraapp.view.hooks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.auroraapp.model.Categoria;
import com.auroraapp.model.Evento;
import com.auroraapp.model.Organizador;
import com.auroraapp.model.Usuario;

public class JsonUtils {

    public static List<Evento> parseEventos(String jsonString) {
        List<Evento> eventos = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Evento evento = new Evento();
            evento.setId(obj.getLong("id"));
            evento.setNome(obj.getString("nome"));
            evento.setDataInicio(LocalDate.parse(obj.getString("dataInicio")));
            evento.setDataFim(LocalDate.parse(obj.getString("dataFim")));
            evento.setValorIngresso(obj.getDouble("valorIngresso"));

            // Participantes
            List<Usuario> participantes = new ArrayList<>();
            JSONArray partArray = obj.optJSONArray("participantes");
            if (partArray != null) {
                for (int j = 0; j < partArray.length(); j++) {
                    JSONObject u = partArray.getJSONObject(j);
                    Usuario user = new Usuario();
                    user.setId(u.getLong("id"));
                    user.setNome(u.getString("nome"));
                    participantes.add(user);
                }
            }
            evento.setParticipantes(participantes);

            // Organizadores
            List<Organizador> organizadores = new ArrayList<>();
            JSONArray orgArray = obj.optJSONArray("organizadores");
            if (orgArray != null) {
                for (int j = 0; j < orgArray.length(); j++) {
                    JSONObject o = orgArray.getJSONObject(j);
                    Organizador org = new Organizador();
                    org.setId(o.getLong("id"));
                    org.setNome(o.getString("nome"));
                    organizadores.add(org);
                }
            }
            evento.setOrganizadores(organizadores);

            // Categorias
            List<Categoria> categorias = new ArrayList<>();
            JSONArray catArray = obj.optJSONArray("categorias");
            if (catArray != null) {
                for (int j = 0; j < catArray.length(); j++) {
                    JSONObject c = catArray.getJSONObject(j);
                    Categoria cat = new Categoria();
                    cat.setId(c.getLong("id"));
                    cat.setNome(c.getString("nome"));
                    categorias.add(cat);
                }
            }
            evento.setCategorias(categorias);

            eventos.add(evento);
        }

        return eventos;
    }
}
