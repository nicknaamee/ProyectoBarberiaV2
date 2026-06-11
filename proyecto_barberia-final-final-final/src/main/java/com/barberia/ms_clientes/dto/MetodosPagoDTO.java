package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodosPagoDTO {

    @NotNull(message = "el id de la sucursal es obligatorio")
    private Long idSucursal;

    @NotNull(message = "el id del metodo de pago es obligatorio")
    private Long idMetodoPago;
}
