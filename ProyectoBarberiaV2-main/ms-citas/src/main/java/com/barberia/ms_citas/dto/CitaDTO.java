package com.barberia.ms_citas.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Data
public class CitaDTO {
    private Long idCita;
    private LocalDate fechaCita;
    private LocalTime horaInicio;
    private String estadoCita;
    
    private BarberoExternoDTO barbero;
}
