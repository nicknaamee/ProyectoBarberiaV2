package com.barberia.ms_barberos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberoDTO {

    @NotBlank(message = "el nombre del barbero es obligatorio")
    private String nombreDelBarbero;

    @NotBlank(message = "la especialidad es obligatoria")
    private String especialidadDelBarbero;

    @NotNull(message = "se debe indicar si esta activo")
    private Boolean estaActivo;
}
