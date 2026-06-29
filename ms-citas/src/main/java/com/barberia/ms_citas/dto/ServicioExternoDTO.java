package com.barberia.ms_citas.dto;

import lombok.Data;

@Data
public class ServicioExternoDTO {

    private Long idDelServicio;
    private String nombreDelServicio;
    private Double precioDelServicio;
    private Integer duracionEnMinutos;
}
