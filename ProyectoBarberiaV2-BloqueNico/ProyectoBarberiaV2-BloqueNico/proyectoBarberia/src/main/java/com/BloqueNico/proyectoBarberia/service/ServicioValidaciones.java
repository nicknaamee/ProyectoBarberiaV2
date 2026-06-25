package com.BloqueNico.proyectoBarberia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.BloqueNico.proyectoBarberia.dto.ServicioDTO;
import com.BloqueNico.proyectoBarberia.model.Servicio;

@Service
public class ServicioValidaciones {

    @SuppressWarnings("unused")
    @Autowired
    private WebClient.Builder webClientBuilder;

    
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

    public ServicioDTO convertirADTO(Servicio servicio) {
        return ServicioDTO.builder()
                .idDelServicio(servicio.getIdDelServicio())
                .nombreDelServicio(servicio.getNombreDelServicio())
                .precioDelServicio(servicio.getPrecioDelServicio())
                .duracionEnMinutos(servicio.getDuracionEnMinutos())
                .build();
    }
}