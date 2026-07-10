package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {

    @NotNull(message = "el id del cliente es obligatorio")
    private Long idDelClienteQueAgenda;

    @NotNull(message = "el id del barbero es obligatorio")
    private Long idDelBarberoAsignado;

    @NotNull(message = "el id del servicio es obligatorio")
    private Long idDelServicioSolicitado;

    @NotNull(message = "la fecha de la cita es obligatoria")
    private LocalDate fechaDeLaCita;

    @NotNull(message = "la hora de inicio es obligatoria")
    private LocalTime horaDeInicio;

    @NotBlank(message = "el estado de la cita es obligatorio")
    private String estadoDeLaCita;
}
