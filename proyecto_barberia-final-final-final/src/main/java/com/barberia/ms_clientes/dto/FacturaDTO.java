package com.barberia.ms_clientes.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FacturaDTO {
    private Long idFactura;
    private Long idCita; 
    private String nombreCliente; 
    private String nombreBarbero;
    private Double montoTotal;
    private String metodoDePago;
    private LocalDateTime fechaEmision;

}