package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {

    @NotNull(message = "el id de la cita es obligatorio")
    private Long idDeLaCitaFacturada;

    @NotNull(message = "el monto total es obligatorio")
    private Double montoTotalCobrado;

    @NotBlank(message = "el metodo de pago es obligatorio")
    private String metodoDePago;

    private LocalDateTime fechaYHoraDeEmision;
}
