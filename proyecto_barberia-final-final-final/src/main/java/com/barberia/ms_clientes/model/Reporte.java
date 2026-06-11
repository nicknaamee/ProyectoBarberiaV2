package com.barberia.ms_clientes.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelReporte;

    private String tipoDeReporte;
    private Double totalDeIngresosCalculado;
    private LocalDate fechaDelReporte;
    private String resumenDelReporte;
}
