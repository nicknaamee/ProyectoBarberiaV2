package com.barberia.ms_productos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductosDTO {

    @NotNull(message = "el id de la sucursal es obligatorio")
    private Long idSucursal;

    @NotNull(message = "el id del producto es obligatorio")
    private Long idProducto;

    @NotNull(message = "el stock es obligatorio")
    private Integer stock;
}
