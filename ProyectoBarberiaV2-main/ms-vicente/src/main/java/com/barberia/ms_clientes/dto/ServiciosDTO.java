package com.barberia.ms_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO de servicios por barbero
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiciosDTO {
    private Long id;
    private Long idBarbero;
    private Long idServicio;
    private Boolean disponible;
}
