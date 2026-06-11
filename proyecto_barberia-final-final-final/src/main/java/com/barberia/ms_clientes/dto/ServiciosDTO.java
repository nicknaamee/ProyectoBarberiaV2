package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiciosDTO {

    @NotNull(message = "el id del barbero es obligatorio")
    private Long idBarbero;

    @NotNull(message = "el id del servicio es obligatorio")
    private Long idServicio;

    @NotNull(message = "se debe indicar si esta disponible")
    private Boolean disponible;
}
