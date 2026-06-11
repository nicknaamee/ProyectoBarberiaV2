package com.barberia.ms_clientes.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El tipo de reporte es obligatorio (ej: Mensual, Diario)")
    @Size(max = 50, message = "El tipo de reporte no puede exceder los 50 caracteres")
    private String tipoDeReporte;

    @NotNull(message = "El total de ingresos no puede ser nulo")
    @PositiveOrZero(message = "El total de ingresos calculado debe ser mayor o igual a cero")
    private Double totalDeIngresosCalculado;

    @NotNull(message = "La fecha del reporte es obligatoria")
    @PastOrPresent(message = "La fecha del reporte no puede ser una fecha futura")
    private LocalDate fechaDelReporte;

    @NotBlank(message = "El resumen del reporte es obligatorio")
    @Size(min = 10, max = 500, message = "El resumen debe tener entre 10 y 500 caracteres")
    private String resumenDelReporte;
}
