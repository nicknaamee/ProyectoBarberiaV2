package com.barberia.ms_clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

// DTO de producto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long idProducto;

    @NotBlank
    private String nombreDelProducto;

    private Integer cantidadEnStock;

    private Double precioUnitarioDelProducto;
}
