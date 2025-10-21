package com.auroraapp.service;

import org.springframework.stereotype.Service;

import com.auroraapp.model.Organizador;
import com.auroraapp.repository.OrganizadorRepositoryJDBC;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizadorService {

    private final OrganizadorRepositoryJDBC organizadorRepository;

    public void salvar(Organizador usuario) {

    }

    public void deletar(Organizador organizador) {

    }

    public Organizador buscarPorId(int id) {
        try {
            return organizadorRepository.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}