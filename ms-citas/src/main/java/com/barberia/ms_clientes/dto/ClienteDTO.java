package com.barberia.ms_clientes.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String telefonoCliente;

}