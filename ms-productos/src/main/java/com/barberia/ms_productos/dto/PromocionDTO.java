package com.barberia.ms_productos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDTO {

    @NotBlank(message = "la descripcion de la promocion es obligatoria")
    private String descripcionDeLaPromocion;

    @NotNull(message = "el porcentaje de descuento es obligatorio")
    @Min(value = 0, message = "el porcentaje no puede ser negativo")
    @Max(value = 100, message = "el porcentaje no puede ser mayor a 100")
    private Integer porcentajeDeDescuento;

    @NotNull(message = "la fecha de inicio es obligatoria")
    private LocalDate fechaInicioDePromocion;

    @NotNull(message = "la fecha de fin es obligatoria")
    private LocalDate fechaFinDePromocion;
}
