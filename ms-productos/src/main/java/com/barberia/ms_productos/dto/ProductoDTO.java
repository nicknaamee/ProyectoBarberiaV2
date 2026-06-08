package com.barberia.ms_productos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    @NotBlank(message = "el nombre del producto es obligatorio")
    private String nombreDelProducto;

    @NotNull(message = "la cantidad en stock es obligatoria")
    private Integer cantidadEnStock;

    @NotNull(message = "el precio unitario es obligatorio")
    private Double precioUnitarioDelProducto;
}
