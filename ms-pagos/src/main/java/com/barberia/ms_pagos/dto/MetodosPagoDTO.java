package com.barberia.ms_pagos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodosPagoDTO {

    @NotNull(message = "el id de la sucursal es obligatorio")
    private Long idSucursal;

    @NotNull(message = "el id del metodo de pago es obligatorio")
    private Long idMetodoPago;
}
