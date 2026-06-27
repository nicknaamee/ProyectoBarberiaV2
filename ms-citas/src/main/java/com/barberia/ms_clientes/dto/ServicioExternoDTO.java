package com.barberia.ms_clientes.dto;

import lombok.Data;

@Data
public class ServicioExternoDTO {

    private Long idDelServicio;
    private String nombreDelServicio;
    private Double precioDelServicio;
    private Integer duracionEnMinutos;
}