package com.barberia.ms_clientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// resumen de reseñas por barbero con promedio de estrellas
@Entity
@Table(name = "resenas_resumen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long barberoId;
    private Integer totalResenas;
    private Double promedioEstrellas;
}
