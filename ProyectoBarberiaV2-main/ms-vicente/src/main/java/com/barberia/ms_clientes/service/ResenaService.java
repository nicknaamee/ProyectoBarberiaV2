package com.barberia.ms_clientes.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ResenaDTO;
import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.repository.ResenaRepository;

import jakarta.transaction.Transactional;

// service de reseñas, un cliente califica a un barbero
@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<ResenaDTO> traerTodos() {
        List<ResenaDTO> listaDTOs = new ArrayList<>();
        for (Resena resena : resenaRepository.findAll()) {
            listaDTOs.add(convertirADTO(resena));
        }
        return listaDTOs;
    }

    public ResenaDTO traerPorId(Long id) {
        Resena resena = resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resena no encontrada con id: " + id));
        return convertirADTO(resena);
    }

    public ResenaDTO crear(ResenaDTO dto) {
        Resena resena = new Resena();
        resena.setIdCliente(dto.getIdCliente());
        resena.setBarberoId(dto.getBarberoId());
        resena.setCalificacionDeEstrellas(dto.getCalificacionDeEstrellas());
        resena.setComentarioDelCliente(dto.getComentarioDelCliente());
        resena.setFechaDeLaResena(LocalDate.now());

        if (resena.getCalificacionDeEstrellas() == null
                || resena.getCalificacionDeEstrellas() < 1
                || resena.getCalificacionDeEstrellas() > 5) {
            throw new RuntimeException("Faltan datos obligatorios de la resena o calificacion fuera de rango");
        }
        return convertirADTO(resenaRepository.save(resena));
    }

    public void eliminar(Long id) {
        resenaRepository.deleteById(id);
    }

    private ResenaDTO convertirADTO(Resena resena) {
        ResenaDTO dto = new ResenaDTO();
        dto.setIdDeLaResena(resena.getIdDeLaResena());
        dto.setIdCliente(resena.getIdCliente());
        dto.setBarberoId(resena.getBarberoId());
        dto.setCalificacionDeEstrellas(resena.getCalificacionDeEstrellas());
        dto.setComentarioDelCliente(resena.getComentarioDelCliente());
        dto.setFechaDeLaResena(resena.getFechaDeLaResena());
        return dto;
    }
}
