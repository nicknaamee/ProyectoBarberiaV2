package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenasDTO {

    @NotNull(message = "el id del barbero es obligatorio")
    private Long idBarbero;

    @NotNull(message = "el total de resenas es obligatorio")
    private Integer totalResenas;

    @NotNull(message = "el promedio de estrellas es obligatorio")
    private Double promedioEstrellas;
}
