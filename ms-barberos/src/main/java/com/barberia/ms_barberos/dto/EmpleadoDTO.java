package com.barberia.ms_barberos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

    @NotBlank(message = "el nombre completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "el cargo es obligatorio")
    private String cargoEnLaBarberia;

    @NotNull(message = "el salario es obligatorio")
    @Positive(message = "el salario debe ser positivo")
    private Double salarioMensual;

    @NotNull(message = "la fecha de contrato es obligatoria")
    private LocalDate fechaDeContrato;
}
