package com.barberia.ms_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO del resumen de reseñas
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenasDTO {
    private Long id;
    private Long barberoId;
    private Integer totalResenas;
    private Double promedioEstrellas;
}
