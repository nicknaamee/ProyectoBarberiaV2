package com.BloqueNico.proyectoBarberia.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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