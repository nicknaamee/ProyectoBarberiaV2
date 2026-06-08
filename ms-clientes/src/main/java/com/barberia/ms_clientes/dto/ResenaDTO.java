package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenaDTO {

    @NotNull(message = "el id del cliente es obligatorio")
    private Long idDelClienteQueOpina;

    @NotNull(message = "el id del barbero es obligatorio")
    private Long idDelBarberoEvaluado;

    @Min(value = 1, message = "la calificacion minima es 1")
    @Max(value = 5, message = "la calificacion maxima es 5")
    @NotNull(message = "la calificacion es obligatoria")
    private Integer calificacionDeEstrellas;

    @Size(max = 500, message = "el comentario no puede tener mas de 500 caracteres")
    private String comentarioDelCliente;

    private LocalDate fechaDeLaResena;
}
