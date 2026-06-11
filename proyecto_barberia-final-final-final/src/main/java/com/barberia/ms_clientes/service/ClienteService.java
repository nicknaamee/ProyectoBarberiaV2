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

    public List<ClienteDTO> traerTodosLosClientes() {
        log.info("buscando todos los clientes en la BD");
        return repositorioDeClientes.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ClienteDTO traerClientePorId(Long idCliente) {
        log.info("buscando cliente con id: {}" + idCliente);
        Cliente cliente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe cliente con id: " + idCliente ));
        return convertirADTO(cliente);
    
    }

    public ClienteDTO guardarNuevoCliente(ClienteDTO datosDelFormulario) {
        log.info("guardando nuevo cliente: {}", datosDelFormulario.getNombreCliente());
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombreCliente(datosDelFormulario.getNombreCliente());
        nuevoCliente.setApellidoCliente(datosDelFormulario.getApellidoCliente());
        nuevoCliente.setEmailCliente(datosDelFormulario.getEmailCliente());
        nuevoCliente.setTelefonoCliente(datosDelFormulario.getTelefonoCliente());
        nuevoCliente.setFechaRegistro(LocalDate.now());
        return convertirADTO(repositorioDeClientes.save(nuevoCliente));
    }

    public ClienteDTO actualizarCliente(Long idCliente, ClienteDTO datosNuevos) {
        log.info("actualizando cliente con id: {}", idCliente);
        Cliente clienteExistente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe cliente con id: " + idCliente));
        clienteExistente.setNombreCliente(datosNuevos.getNombreCliente());
        clienteExistente.setApellidoCliente(datosNuevos.getApellidoCliente());
        clienteExistente.setEmailCliente(datosNuevos.getEmailCliente());
        clienteExistente.setTelefonoCliente(datosNuevos.getTelefonoCliente());
        return convertirADTO(repositorioDeClientes.save(clienteExistente));
    }

    public void eliminarCliente(Long idCliente) {
        log.info("eliminando cliente con id: " + idCliente);
        if(!repositorioDeClientes.existsById(idCliente)) {
            throw new RuntimeException("No existe cliente con ese Id");
        }
        repositorioDeClientes.deleteById(idCliente);

    }

    private ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombreCliente(cliente.getNombreCliente());
        dto.setApellidoCliente(cliente.getApellidoCliente());
        dto.setEmailCliente(cliente.getEmailCliente());
        dto.setTelefonoCliente(cliente.getTelefonoCliente());
        return dto;
    }

}
