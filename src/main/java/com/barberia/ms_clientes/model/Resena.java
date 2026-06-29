package com.barberia.ms_clientes.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// modelo de reseña de un cliente hacia un barbero
@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaResena; // id de la reseña

    private Long idCliente; // id del cliente que la escribio (otro microservicio)
    private Long barberoId; // id del barbero evaluado (otro microservicio)

    private Integer calificacionDeEstrellas; // estrellitas, del 1 al 5
    private String comentarioDelCliente; // lo que dijo el cliente
    private LocalDate fechaDeLaResena; // cuando la escribio
}
