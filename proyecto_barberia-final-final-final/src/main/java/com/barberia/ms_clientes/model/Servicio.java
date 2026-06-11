package com.barberia.ms_clientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicios") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelServicio;

    private String nombreDelServicio;
    private Double precioDelServicio;
    private Integer duracionEnMinutos;
}
