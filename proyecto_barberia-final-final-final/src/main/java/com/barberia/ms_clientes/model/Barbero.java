package com.barberia.ms_clientes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "barberos")
@Data
public class Barbero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarbero;
    
    @Column(nullable = false, length = 50)
    private String nombreBarbero;

    @Column(nullable = false, length = 50)
    private String especialidadBarbero;

    @Column(nullable = false, length = 50)
    private String telefonoBarbero;

    @Column(nullable = false)
    private Boolean estado = true;
}