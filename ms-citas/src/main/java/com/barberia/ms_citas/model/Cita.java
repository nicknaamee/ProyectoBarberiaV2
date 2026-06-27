
package com.barberia.ms_clientes.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @JoinColumn(name = "id_barbero")
    private Long idBarbero;

    @JoinColumn(name = "id_cliente")
    private Long idCliente;

    private LocalDate fechaCita;
    private LocalTime horaInicio;
    private String estadoCita;

    @JoinColumn(name = "id_servicio")
    private Long idServicio;
}