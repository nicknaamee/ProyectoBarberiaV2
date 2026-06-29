package com.BloqueNico.proyectoBarberia.service;

import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.model.Cliente;

@Service
public class ClienteValidaciones {

    public Boolean validarCliente(Cliente cliente) {
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
            return false;
        }
        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()) {
            return false;
        }
        if (cliente.getEmailCliente() == null || cliente.getEmailCliente().trim().isEmpty()) {
            return false;
        }
        if (cliente.getTelefonoCliente() == null || cliente.getTelefonoCliente().trim().isEmpty()) {
            return false;
        }
        if (cliente.getNombreCliente().length() > 15 || cliente.getApellidoCliente().length() > 15) {
            return false; 
        }
        if (!cliente.getEmailCliente().contains("@") || !cliente.getEmailCliente().contains(".")) {
            return false;
        }
        String tel = cliente.getTelefonoCliente().trim();
        if (!tel.matches("^[0-9]{9,12}$")) {
            return false;
        }
        return true;
    }
}