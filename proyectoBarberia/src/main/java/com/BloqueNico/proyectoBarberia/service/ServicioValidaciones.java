package com.BloqueNico.proyectoBarberia.service;

import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.model.Servicio;

@Service
public class ServicioValidaciones {

    public Boolean validarServicio(Servicio servicio) {
        if (servicio.getNombreDelServicio() == null || servicio.getNombreDelServicio().trim().isEmpty()) {
            return false;
        }
        if (servicio.getPrecioDelServicio() == null || servicio.getDuracionEnMinutos() == null) {
            return false;
        }
        if (servicio.getNombreDelServicio().length() > 100) {
            return false;
        }
        if (servicio.getPrecioDelServicio() <= 0) {
            return false;
        }
        if (servicio.getDuracionEnMinutos() < 5 || servicio.getDuracionEnMinutos() > 480) {
            return false;
        }
        return true;
    }
}