package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicioDTO {

    private Long idDelServicio;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(max = 100, message = "El nombre del servicio no puede superar los 100 caracteres")
    private String nombreDelServicio;

    @NotNull(message = "El precio del servicio no puede ser nulo")
    @Positive(message = "El precio del servicio debe ser un valor positivo")
    private Double precioDelServicio;

    @NotNull(message = "La duración del servicio es obligatoria")
    @Min(value = 5, message = "La duración mínima de un servicio debe ser de 5 minutos")
    @Max(value = 480, message = "La duración no puede exceder los 480 minutos (8 horas)")
    private Integer duracionEnMinutos;
}
