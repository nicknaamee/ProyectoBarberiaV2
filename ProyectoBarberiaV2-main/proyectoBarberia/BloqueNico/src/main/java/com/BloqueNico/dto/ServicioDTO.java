package com.BloqueNico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioDTO {

    private Long idDelServicio;
    private String nombreDelServicio;
    private Double precioDelServicio;
    private Integer duracionEnMinutos;
}
