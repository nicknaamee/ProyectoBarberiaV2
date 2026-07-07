package com.BloqueNico.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClienteDTO {
    private Long idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String telefonoCliente;

}
