package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity 
@Table(name = "facturas") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Factura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaFactura;

    private Long idDeLaCitaFacturada;
    private Double montoTotalCobrado;
    private String metodoDePago;
    private LocalDateTime fechaYHoraDeEmision;
}
