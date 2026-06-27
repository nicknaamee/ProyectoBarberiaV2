package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoDTO {

    @NotBlank(message = "el nombre del metodo es obligatorio")
    private String nombre;

    @NotNull(message = "se debe indicar si esta activo")
    private Boolean activo;
}
