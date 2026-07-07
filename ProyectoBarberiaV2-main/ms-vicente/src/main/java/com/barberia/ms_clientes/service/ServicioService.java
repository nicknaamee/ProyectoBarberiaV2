package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ServicioDTO;
import com.barberia.ms_clientes.model.Servicio;
import com.barberia.ms_clientes.repository.ServicioRepository;

import jakarta.transaction.Transactional;

// service de servicios que ofrece la barberia (cortes, barba, etc)
@Service
@Transactional
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<ServicioDTO> traerTodos() {
        List<ServicioDTO> listaDTOs = new ArrayList<>();
        for (Servicio servicio : servicioRepository.findAll()) {
            listaDTOs.add(convertirADTO(servicio));
        }
        return listaDTOs;
    }

    public ServicioDTO traerPorId(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
        return convertirADTO(servicio);
    }

    public ServicioDTO crear(ServicioDTO dto) {
        Servicio servicio = new Servicio();
        servicio.setNombreDelServicio(dto.getNombreDelServicio());
        servicio.setPrecioDelServicio(dto.getPrecioDelServicio());
        servicio.setDuracionEnMinutos(dto.getDuracionEnMinutos());

        if (servicio.getNombreDelServicio() == null || servicio.getNombreDelServicio().isBlank()) {
            throw new RuntimeException("Faltan datos obligatorios del servicio");
        }
        return convertirADTO(servicioRepository.save(servicio));
    }

    public ServicioDTO actualizar(Long id, ServicioDTO dto) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));

        if (dto.getNombreDelServicio() != null) {
            servicio.setNombreDelServicio(dto.getNombreDelServicio());
        }
        if (dto.getPrecioDelServicio() != null) {
            servicio.setPrecioDelServicio(dto.getPrecioDelServicio());
        }
        if (dto.getDuracionEnMinutos() != null) {
            servicio.setDuracionEnMinutos(dto.getDuracionEnMinutos());
        }
        return convertirADTO(servicioRepository.save(servicio));
    }

    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }

    private ServicioDTO convertirADTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setIdDelServicio(servicio.getIdDelServicio());
        dto.setNombreDelServicio(servicio.getNombreDelServicio());
        dto.setPrecioDelServicio(servicio.getPrecioDelServicio());
        dto.setDuracionEnMinutos(servicio.getDuracionEnMinutos());
        return dto;
    }
}
