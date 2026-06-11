package com.barberia.ms_clientes.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {

    private Long idCita;

    @NotBlank(message = "El nombre del barbero es obligatorio")
    private String nombreBarbero;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "La fecha no puede ser nula")
    @Future(message = "La fecha debe ser futura")
    private LocalDate fechaCita;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime horaInicio;

    private String estadoCita;
}