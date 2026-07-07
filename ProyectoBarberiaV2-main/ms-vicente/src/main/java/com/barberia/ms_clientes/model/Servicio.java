package com.barberia.ms_clientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// cada servicio que ofrece la barberia (corte, barba, etc)
@Entity
@Table(name = "servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelServicio; // id unico del servicio

    private String nombreDelServicio; // nombre tipo "corte clasico"
    private Double precioDelServicio; // cuanto cobran por eso
    private Integer duracionEnMinutos; // cuanto tarda en minutos
}
