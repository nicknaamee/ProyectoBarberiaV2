package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ServiciosDTO;
import com.barberia.ms_clientes.model.Servicios;
import com.barberia.ms_clientes.repository.ServiciosRepository;

import jakarta.transaction.Transactional;

// service que relaciona cada barbero con los servicios que ofrece
@Service
@Transactional
public class ServiciosService {

    @Autowired
    private ServiciosRepository serviciosRepository;

    public List<ServiciosDTO> traerTodos() {
        List<ServiciosDTO> listaDTOs = new ArrayList<>();
        for (Servicios servicios : serviciosRepository.findAll()) {
            listaDTOs.add(convertirADTO(servicios));
        }
        return listaDTOs;
    }

    public ServiciosDTO traerPorId(Long id) {
        Servicios servicios = serviciosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio de barbero no encontrado con id: " + id));
        return convertirADTO(servicios);
    }

    public ServiciosDTO crear(ServiciosDTO dto) {
        Servicios servicios = new Servicios();
        servicios.setIdBarbero(dto.getIdBarbero());
        servicios.setIdServicio(dto.getIdServicio());
        servicios.setDisponible(dto.getDisponible());

        if (servicios.getIdBarbero() == null || servicios.getIdServicio() == null) {
            throw new RuntimeException("Faltan datos obligatorios del servicio por barbero");
        }
        return convertirADTO(serviciosRepository.save(servicios));
    }

    public ServiciosDTO actualizar(Long id, ServiciosDTO dto) {
        Servicios servicios = serviciosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio de barbero no encontrado con id: " + id));

        if (dto.getIdBarbero() != null) {
            servicios.setIdBarbero(dto.getIdBarbero());
        }
        if (dto.getIdServicio() != null) {
            servicios.setIdServicio(dto.getIdServicio());
        }
        if (dto.getDisponible() != null) {
            servicios.setDisponible(dto.getDisponible());
        }
        return convertirADTO(serviciosRepository.save(servicios));
    }

    public void eliminar(Long id) {
        serviciosRepository.deleteById(id);
    }

    private ServiciosDTO convertirADTO(Servicios servicios) {
        ServiciosDTO dto = new ServiciosDTO();
        dto.setId(servicios.getId());
        dto.setIdBarbero(servicios.getIdBarbero());
        dto.setIdServicio(servicios.getIdServicio());
        dto.setDisponible(servicios.getDisponible());
        return dto;
    }
}
