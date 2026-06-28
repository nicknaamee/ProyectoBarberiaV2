package com.BloqueNico.proyectoBarberia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_del_reporte")
    private Long idDelReporte;

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Size(min = 3, max = 50, message = "El tipo de reporte debe tener entre 3 y 50 caracteres")
    @Column(name = "tipo_de_reporte", nullable = false, length = 50)
    private String tipoDeReporte;

    @NotNull(message = "El total de ingresos calculado es obligatorio")
    @Min(value = 0, message = "El total de ingresos calculado no puede ser un número negativo")
    @Column(name = "total_de_ingresos_calculado", nullable = false)
    private Double totalDeIngresosCalculado;

    @NotNull(message = "La fecha del reporte es obligatoria")
    @PastOrPresent(message = "La fecha del reporte no puede ser una fecha futura")
    @Column(name = "fecha_del_reporte", nullable = false)
    private LocalDate fechaDelReporte;

    @NotBlank(message = "El resumen del reporte es obligatorio")
    @Size(min = 10, max = 1000, message = "El resumen debe tener entre 10 y 1000 caracteres")
    @Column(name = "resumen_del_reporte", nullable = false, length = 1000)
    private String resumenDelReporte;
}