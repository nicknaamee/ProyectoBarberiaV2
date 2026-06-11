package com.barberia.ms_clientes.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idDelClienteQueOpina;

    @NotNull(message = "El ID del barbero es obligatorio")
    private Long idDelBarberoEvaluado;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1 estrella")
    @Max(value = 5, message = "La calificación máxima es 5 estrellas")
    private Integer calificacionDeEstrellas;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 500, message = "El comentario no puede superar los 500 caracteres")
    private String comentarioDelCliente;

    @PastOrPresent(message = "La fecha de la reseña no puede ser futura")
    private LocalDate fechaDeLaResena;
}