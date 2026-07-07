package com.barberia.ms_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO de productos por sucursal
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductosDTO {
    private Long id;
    private Long idSucursal;
    private Long productoId;
    private Integer stock;
}
