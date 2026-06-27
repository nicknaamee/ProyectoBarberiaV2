package com.BloqueNico.proyectoBarberia.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteDTO {
    private Long idDelReporte;
    private String tipoDeReporte;
    private Double totalDeIngresosCalculado;
    private LocalDate fechaDelReporte;
    private String resumenDelReporte;
}
