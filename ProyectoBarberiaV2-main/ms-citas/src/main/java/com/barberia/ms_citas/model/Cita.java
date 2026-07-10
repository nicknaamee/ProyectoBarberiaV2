
package com.barberia.ms_citas.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
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

    @Column(name = "id_barbero")
    private Long idBarbero;

    @Column(name = "id_cliente")
    private Long idCliente;

    private LocalDate fechaCita;
    private LocalTime horaInicio;
    private String estadoCita;

    @Column(name = "id_servicio")
    private Long idServicio;
}
