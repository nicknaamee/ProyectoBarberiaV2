package com.barberia.ms_barberos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {

    @NotBlank(message = "el nombre del servicio es obligatorio")
    private String nombreDelServicio;

    @NotNull(message = "el precio del servicio es obligatorio")
    @Positive(message = "el precio debe ser positivo")
    private Double precioDelServicio;

    @NotNull(message = "la duracion es obligatoria")
    @Min(value = 1, message = "la duracion minima es 1 minuto")
    private Integer duracionEnMinutos;
}
