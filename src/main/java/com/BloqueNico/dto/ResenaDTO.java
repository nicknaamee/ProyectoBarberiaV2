package com.BloqueNico.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResenaDTO {
    private Long idDeLaResena;
    private Long idDelClienteQueOpina;
    private Long idDelBarberoEvaluado;
    private Integer calificacionDeEstrellas;
    private String comentarioDelCliente;
    private LocalDate fechaDeLaResena;
}