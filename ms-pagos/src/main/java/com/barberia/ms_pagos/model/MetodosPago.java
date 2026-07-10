package com.barberia.ms_pagos.model;

import jakarta.persistence.*;
import lombok.*;

// esta tabla guarda que metodos de pago acepta cada sucursal
@Entity
@Table(name = "metodos_pago_sucursal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodosPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idSucursal;
    private Long idMetodoPago;
}
