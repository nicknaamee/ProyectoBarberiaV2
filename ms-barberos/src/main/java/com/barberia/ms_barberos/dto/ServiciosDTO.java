package com.barberia.ms_barberos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

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
