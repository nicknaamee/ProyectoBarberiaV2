package com.barberia.ms_clientes.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {

    private Long idCliente;

    @NotBlank(message="el nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotBlank(message="el apellido del cliente es obligatorio")
    private String apellidoCliente;

    @Email(message="el email debe tener formato valido: ejemplo@correo.com")
    @NotBlank(message = "el email es obligatorio")
    private String emailCliente;
    
    @NotBlank(message="El teléfono es obligatorio")
    @Size(min = 9, max = 12, message = "el telefono debe tener entre 9 y 12 caracteres")
    private String telefonoCliente;

}
