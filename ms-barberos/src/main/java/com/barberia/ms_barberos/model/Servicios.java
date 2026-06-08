package com.barberia.ms_barberos.model;

import jakarta.persistence.*;
import lombok.*;

// esta tabla guarda que servicios ofrece cada barbero
@Entity
@Table(name = "servicios_barbero")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idBarbero;
    private Long idServicio;
    private Boolean disponible;
}
