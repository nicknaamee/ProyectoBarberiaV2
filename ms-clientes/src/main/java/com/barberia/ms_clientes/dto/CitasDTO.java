package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitasDTO {

    @NotNull(message = "el id del cliente es obligatorio")
    private Long idCliente;

    @NotNull(message = "el total de citas es obligatorio")
    private Integer totalCitas;
}
