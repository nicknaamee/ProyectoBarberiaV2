package com.barberia.ms_pagos.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {

    @NotBlank(message = "el tipo de reporte es obligatorio")
    private String tipoDeReporte;

    @NotNull(message = "el total de ingresos es obligatorio")
    private Double totalDeIngresosCalculado;

    private LocalDate fechaDelReporte;

    @NotBlank(message = "el resumen del reporte es obligatorio")
    private String resumenDelReporte;
}
