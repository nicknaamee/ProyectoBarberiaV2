package com.BloqueNico.proyectoBarberia.service;

import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.model.Barbero;

@Service
public class BarberoValidaciones {

    public Boolean validarBarbero(Barbero barbero) {
    if (barbero.getNombreBarbero() == null || barbero.getNombreBarbero().trim().isEmpty()) {
        return false;
    }
    if (barbero.getEspecialidadBarbero() == null || barbero.getEspecialidadBarbero().trim().isEmpty()) {
        return false;
    }
    if (barbero.getTelefonoBarbero() == null || barbero.getTelefonoBarbero().trim().isEmpty()) {
        return false;
    }
    if (barbero.getNombreBarbero().length() > 50 || barbero.getNombreBarbero().length() < 3) {
        return false;
    }
    if (barbero.getEspecialidadBarbero().length() > 50) {
        return false;
    }
    if (barbero.getTelefonoBarbero().length() > 50) {
        return false;
    }
    if (!barbero.getTelefonoBarbero().trim().matches("^\\+?[0-9]{8,15}$")) {
        return false; 
    }
    return true;
    }
}