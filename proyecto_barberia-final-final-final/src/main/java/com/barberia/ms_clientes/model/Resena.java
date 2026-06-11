package com.barberia.ms_clientes.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Resena {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaResena;

    @ManyToOne
    @JoinColumn(name="id_opinion_cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="id_barbero_evaluado")
    private Barbero barbero;

    private Integer calificacionDeEstrellas;
    private String comentarioDelCliente;
    private LocalDate fechaDeLaResena;
}
