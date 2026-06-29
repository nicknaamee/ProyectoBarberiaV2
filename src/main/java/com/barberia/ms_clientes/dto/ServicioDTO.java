package com.barberia.ms_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

// DTO de servicio
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {
    private Long idDelServicio;

    @NotBlank
    private String nombreDelServicio;

    private Double precioDelServicio;

    private Integer duracionEnMinutos;
}
