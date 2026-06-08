package com.barberia.ms_productos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {

    @NotBlank(message = "el nombre del proveedor es obligatorio")
    private String nombreDelProveedor;

    @NotBlank(message = "el email de contacto es obligatorio")
    @Email(message = "el email debe tener un formato valido")
    private String emailDeContacto;

    @NotBlank(message = "el telefono de contacto es obligatorio")
    private String telefonoDeContacto;

    @NotBlank(message = "el producto que nos provee es obligatorio")
    private String productoQueNosProvee;
}
