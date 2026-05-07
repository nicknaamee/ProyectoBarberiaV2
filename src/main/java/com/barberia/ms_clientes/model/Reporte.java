package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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
