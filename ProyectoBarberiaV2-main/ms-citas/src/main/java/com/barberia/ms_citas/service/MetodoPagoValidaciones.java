package com.barberia.ms_citas.service;

import org.springframework.stereotype.Service;

import com.barberia.ms_citas.dto.MetodoPagoDTO;
import com.barberia.ms_citas.model.MetodoPago;

@Service
public class MetodoPagoValidaciones {

    public MetodoPagoDTO convertirADTO(MetodoPago metodo) {
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setId(metodo.getId());
        dto.setNombre(metodo.getNombre());
        dto.setActivo(metodo.getActivo());
        return dto;
    }

    public Boolean validarNullVacio(MetodoPagoDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            return false;
        }
        if (dto.getActivo() == null) {
            return false;
        }
        return true;
    }
}

