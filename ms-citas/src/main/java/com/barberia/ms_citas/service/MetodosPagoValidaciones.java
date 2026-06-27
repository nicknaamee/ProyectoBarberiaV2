package com.barberia.ms_clientes.service;

import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.MetodosPagoDTO;
import com.barberia.ms_clientes.model.MetodosPago;

@Service
public class MetodosPagoValidaciones {

    public MetodosPagoDTO convertirADTO(MetodosPago metodo) {
        MetodosPagoDTO dto = new MetodosPagoDTO();
        dto.setId(metodo.getId());
        dto.setIdSucursal(metodo.getIdSucursal());
        dto.setIdMetodoPago(metodo.getIdMetodoPago());
        return dto;
    }

    public Boolean validarNullVacio(MetodosPagoDTO dto) {
        if (dto.getIdSucursal() == null || dto.getIdMetodoPago() == null) {
            return false;
        }
        return true;
    }
}
