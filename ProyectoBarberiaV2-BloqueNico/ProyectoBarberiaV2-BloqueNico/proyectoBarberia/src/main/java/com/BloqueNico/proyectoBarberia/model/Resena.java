package com.BloqueNico.proyectoBarberia.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaResena;

    @NotNull(message = "El objeto cliente no puede ser nulo")
    @ManyToOne
    @JoinColumn(name="id_opinion_cliente")
    private Cliente cliente;
    
    @NotNull(message = "El objeto barbero no puede ser nulo")
    @ManyToOne
    @JoinColumn(name="id_barbero_evaluado")
    private Barbero barbero;

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