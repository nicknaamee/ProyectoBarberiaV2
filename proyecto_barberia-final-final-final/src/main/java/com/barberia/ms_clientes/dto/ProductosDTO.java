package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
