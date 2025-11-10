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

    public static List<Categoria> parseCategoria(String jsonString) {
        List<Categoria> categorias = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Categoria categoria = new Categoria();
            categoria.setId(obj.getLong("id"));
            categoria.setNome(obj.getString("nome"));

            categorias.add(categoria);
        }

        return categorias;
    }

    public static String toJson(Evento evento) {
        JSONObject obj = new JSONObject();
        obj.put("id", evento.getId());
        obj.put("nome", evento.getNome());
        obj.put("dataInicio", evento.getDataInicio() != null ? evento.getDataInicio().toString() : JSONObject.NULL);
        obj.put("dataFim", evento.getDataFim() != null ? evento.getDataFim().toString() : JSONObject.NULL);
        obj.put("valorIngresso", evento.getValorIngresso());

        JSONArray participantes = new JSONArray();
        if (evento.getParticipantes() != null) {
            for (Usuario u : evento.getParticipantes()) {
                JSONObject ju = new JSONObject();
                ju.put("id", u.getId());
                ju.put("nome", u.getNome());
                participantes.put(ju);
            }
        }
        obj.put("participantes", participantes);

        JSONArray organizadores = new JSONArray();
        if (evento.getOrganizadores() != null) {
            for (Organizador o : evento.getOrganizadores()) {
                JSONObject jo = new JSONObject();
                jo.put("id", o.getId());
                jo.put("nome", o.getNome());
                organizadores.put(jo);
            }
        }
        obj.put("organizadores", organizadores);

        JSONArray categorias = new JSONArray();
        if (evento.getCategorias() != null) {
            for (Categoria c : evento.getCategorias()) {
                JSONObject jc = new JSONObject();
                jc.put("id", c.getId());
                jc.put("nome", c.getNome());
                categorias.put(jc);
            }
        }
        obj.put("categorias", categorias);

        return obj.toString();
    }
}
