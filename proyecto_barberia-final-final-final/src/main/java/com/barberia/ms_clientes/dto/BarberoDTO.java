package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BarberoDTO {
    private Long idBarbero;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50)
    private String nombreBarbero;

    @NotBlank(message = "La especialidad del barbero es obligatoria")
    private String especialidadBarbero;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefonoBarbero;
    
}