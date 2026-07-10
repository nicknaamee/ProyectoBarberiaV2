package com.barberia.ms_barberos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {

    @NotBlank(message = "el nombre de la sucursal es obligatorio")
    private String nombreDeLaSucursal;

    @NotBlank(message = "la direccion es obligatoria")
    private String direccionCompleta;

    @NotBlank(message = "el telefono es obligatorio")
    private String telefonoDeLaSucursal;

    @NotNull(message = "se debe indicar si esta abierta")
    private Boolean estaAbierta;
}
