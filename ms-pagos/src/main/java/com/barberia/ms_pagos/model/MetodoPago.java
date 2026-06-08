package com.barberia.ms_pagos.model;

import jakarta.persistence.*;
import lombok.*;

// esta tabla guarda los metodos de pago (efectivo, tarjeta, etc)
@Entity
@Table(name = "metodos_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Boolean activo;
}
