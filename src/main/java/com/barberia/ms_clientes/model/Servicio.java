package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;

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
