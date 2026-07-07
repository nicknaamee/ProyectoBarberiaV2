package com.barberia.ms_clientes.dto;

import java.time.LocalDate;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

// DTO de reseña: trae el cliente y el barbero desde otros microservicios
@Data
public class ResenaDTO {
    private Long idDeLaResena;
    private Long idCliente;
    private Long barberoId;

    @Min(1)
    @Max(5)
    private Integer calificacionDeEstrellas;

    private String comentarioDelCliente;
    private LocalDate fechaDeLaResena;

}
