package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
