package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ClienteDTO;
import com.barberia.ms_clientes.model.Cliente;
import com.barberia.ms_clientes.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j

public class ClienteService {
    @Autowired
    private ClienteRepository repositorioDeClientes;

    public List<Cliente> traerTodosLosClientes() {
        log.info("buscando todos los clientes en la BD");
        return repositorioDeClientes.findAll();
    }

    public Cliente traerClientePorId(Long idDelCliente) {
        log.info("buscando cliente con id: {}", idDelCliente);
        return repositorioDeClientes.findById(idDelCliente)
            .orElseThrow(() -> new RuntimeException(
                "no existe ningun cliente con el id: " + idDelCliente));
    }

    public Cliente guardarNuevoCliente(ClienteDTO datosDelFormulario) {
        log.info("guardando nuevo cliente: {}", datosDelFormulario.getNombreDelCliente());
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombreDelCliente(datosDelFormulario.getNombreDelCliente());
        nuevoCliente.setApellidoDelCliente(datosDelFormulario.getApellidoDelCliente());
        nuevoCliente.setEmailDelCliente(datosDelFormulario.getEmailDelCliente());
        nuevoCliente.setTelefonoDelCliente(datosDelFormulario.getTelefonoDelCliente());
        nuevoCliente.setFechaRegistro(LocalDate.now());
        return repositorioDeClientes.save(nuevoCliente);
    }

    public Cliente actualizarCliente(Long idDelCliente, ClienteDTO datosNuevos) {
        log.info("actualizando cliente con id: {}", idDelCliente);
        Cliente clienteExistente = traerClientePorId(idDelCliente);
        clienteExistente.setNombreDelCliente(datosNuevos.getNombreDelCliente());
        clienteExistente.setApellidoDelCliente(datosNuevos.getApellidoDelCliente());
        clienteExistente.setEmailDelCliente(datosNuevos.getEmailDelCliente());
        clienteExistente.setTelefonoDelCliente(datosNuevos.getTelefonoDelCliente());
        return repositorioDeClientes.save(clienteExistente);
    }

    public void eliminarCliente(Long idDelCliente) {
        log.info("eliminando cliente con id: {}", idDelCliente);
        traerClientePorId(idDelCliente);
        repositorioDeClientes.deleteById(idDelCliente);
        log.info("cliente {} eliminado correctamente", idDelCliente);
    }

}
