package com.BloqueNico.proyectoBarberia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
@Table(name = "resena")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_de_la_resena")
    private Long idDeLaResena;

    @NotNull(message = "El cliente que opina es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_opinion_cliente", nullable = false) // Candado físico en MySQL
    private Cliente cliente;
    
    @NotNull(message = "El barbero evaluado es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_barbero_evaluado", nullable = false) // Candado físico en MySQL
    private Barbero barbero;

    @NotNull(message = "La calificación en estrellas es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1 estrella")
    @Max(value = 5, message = "La calificación máxima es 5 estrellas")
    @Column(name = "calificacion_de_estrellas", nullable = false)
    private Integer calificacionDeEstrellas;

    @NotBlank(message = "El comentario del cliente no puede estar vacío")
    @Size(min = 5, max = 500, message = "El comentario debe tener entre 5 and 500 caracteres")
    @Column(name = "comentario_del_cliente", nullable = false, length = 500)
    private String comentarioDelCliente;

    @NotNull(message = "La fecha de la reseña es obligatoria")
    @PastOrPresent(message = "La fecha de la reseña no puede ser una fecha futura")
    @Column(name = "fecha_de_la_resena", nullable = false)
    private LocalDate fechaDeLaResena;
}