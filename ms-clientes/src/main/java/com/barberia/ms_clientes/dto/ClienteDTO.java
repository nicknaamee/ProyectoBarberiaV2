package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {

    @NotBlank(message = "el nombre del cliente es obligatorio")
    private String nombreDelCliente;

    @NotBlank(message = "el apellido del cliente es obligatorio")
    private String apellidoDelCliente;

    @Email(message = "el email debe tener formato valido: ejemplo@correo.com")
    @NotBlank(message = "el email es obligatorio")
    private String emailDelCliente;
    
    @Size(min = 9, max = 12, message = "el telefono debe tener entre 9 y 12 caracteres")
    private String telefonoDelCliente;

}
