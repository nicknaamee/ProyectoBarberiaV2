package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ResenasDTO;
import com.barberia.ms_clientes.model.Resenas;
import com.barberia.ms_clientes.repository.ResenasRepository;

import jakarta.transaction.Transactional;

// service del resumen de reseñas: total y promedio de estrellas por barbero
@Service
@Transactional
public class ResenasService {

    @Autowired
    private ResenasRepository resenasRepository;

    public List<ResenasDTO> traerTodos() {
        List<ResenasDTO> listaDTOs = new ArrayList<>();
        for (Resenas resumen : resenasRepository.findAll()) {
            listaDTOs.add(convertirADTO(resumen));
        }
        return listaDTOs;
    }

    public ResenasDTO traerPorId(Long id) {
        Resenas resumen = resenasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resumen de resenas no encontrado con id: " + id));
        return convertirADTO(resumen);
    }

    public ResenasDTO crear(ResenasDTO dto) {
        Resenas resumen = new Resenas();
        resumen.setBarberoId(dto.getBarberoId());
        resumen.setTotalResenas(dto.getTotalResenas());
        resumen.setPromedioEstrellas(dto.getPromedioEstrellas());

        if (resumen.getBarberoId() == null) {
            throw new RuntimeException("Faltan datos obligatorios del resumen de resenas");
        }
        return convertirADTO(resenasRepository.save(resumen));
    }

    public ResenasDTO actualizar(Long id, ResenasDTO dto) {
        Resenas resumen = resenasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resumen de resenas no encontrado con id: " + id));

        if (dto.getBarberoId() != null) {
            resumen.setBarberoId(dto.getBarberoId());
        }
        if (dto.getTotalResenas() != null) {
            resumen.setTotalResenas(dto.getTotalResenas());
        }
        if (dto.getPromedioEstrellas() != null) {
            resumen.setPromedioEstrellas(dto.getPromedioEstrellas());
        }
        return convertirADTO(resenasRepository.save(resumen));
    }

    public void eliminar(Long id) {
        resenasRepository.deleteById(id);
    }

    private ResenasDTO convertirADTO(Resenas resumen) {
        ResenasDTO dto = new ResenasDTO();
        dto.setId(resumen.getId());
        dto.setBarberoId(resumen.getBarberoId());
        dto.setTotalResenas(resumen.getTotalResenas());
        dto.setPromedioEstrellas(resumen.getPromedioEstrellas());
        return dto;
    }
}
