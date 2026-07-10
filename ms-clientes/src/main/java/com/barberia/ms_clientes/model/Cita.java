package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity 
@Table(name = "citas") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Cita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaCita;

    private Long idDelClienteQueAgenda;
    private Long idDelBarberoAsignado;
    private Long idDelServicioSolicitado;

    private LocalDate fechaDeLaCita;
    private LocalTime horaDeInicio;
    private String estadoDeLaCita;
}