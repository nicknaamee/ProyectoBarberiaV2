package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;

// esta tabla guarda el resumen de resenas de cada barbero
@Entity
@Table(name = "resenas_resumen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idBarbero;
    private Integer totalResenas;
    private Double promedioEstrellas;
}
