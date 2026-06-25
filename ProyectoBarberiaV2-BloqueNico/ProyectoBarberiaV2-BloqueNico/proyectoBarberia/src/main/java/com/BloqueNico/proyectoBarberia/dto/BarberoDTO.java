package com.BloqueNico.proyectoBarberia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BarberoDTO {

    private Long idBarbero;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del barbero debe tener entre 3 y 50 caracteres")
    private String nombreBarbero;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 3, max = 50, message = "La especialidad debe tener entre 3 y 50 caracteres")
    private String especialidadBarbero;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
    private String telefonoBarbero;

    @NotNull(message = "El estado del barbero es obligatorio")
    private Boolean estado;
}