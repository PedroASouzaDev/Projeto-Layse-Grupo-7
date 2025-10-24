package com.auroraapp.service;

import javax.naming.NameNotFoundException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.auroraapp.model.Organizador;
import com.auroraapp.repository.OrganizadorRepositoryJDBC;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizadorService {

    private final OrganizadorRepositoryJDBC organizadorRepository;

    public boolean salvar(Organizador usuario) throws DuplicateKeyException{
        if(null != organizadorRepository.buscarPorId(usuario.getId()))
        {
            throw new DuplicateKeyException("Organizador ja existe!");
        }

        try {
            organizadorRepository.salvar(usuario);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deletar(Organizador organizador) throws NameNotFoundException {
        if(null == organizadorRepository.buscarPorId(organizador.getId()))
        {
            throw new NameNotFoundException("Organizador não encontrado");
        }

        try {
            organizadorRepository.deletar(organizador);
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

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