package com.BloqueNico.proyectoBarberia.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.BloqueNico.proyectoBarberia.dto.ClienteDTO;
import com.BloqueNico.proyectoBarberia.model.Cliente;
import com.BloqueNico.proyectoBarberia.repository.ClienteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j

public class ClienteService {
    @Autowired
    private ClienteRepository repositorioDeClientes;

    public List<ClienteDTO> traerTodosLosClientes() {
        log.info("Buscando todos los clientes en la BD");
        return repositorioDeClientes.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ClienteDTO traerClientePorId(Long idCliente) {
        log.info("Buscando cliente con id: {}", idCliente);
        Cliente cliente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe cliente con id: " + idCliente ));
        return convertirADTO(cliente);
    
    }

    public ClienteDTO guardarNuevoCliente(ClienteDTO datosDelFormulario) {
        log.info("Guardando nuevo cliente: {}", datosDelFormulario.getNombreCliente());
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombreCliente(datosDelFormulario.getNombreCliente());
        nuevoCliente.setApellidoCliente(datosDelFormulario.getApellidoCliente());
        nuevoCliente.setEmailCliente(datosDelFormulario.getEmailCliente());
        nuevoCliente.setTelefonoCliente(datosDelFormulario.getTelefonoCliente());
        nuevoCliente.setFechaRegistro(LocalDate.now());
        
        // Simulación de comunicación REST con WebClient
        try {
            WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");
            String response = webClient.get()
                .uri("/users/1")
                .retrieve()
                .bodyToMono(String.class)
                .block();
            log.info("Comunicación REST exitosa (WebClient): {}", response);
        } catch (Exception e) {
            log.error("Error en comunicación REST externa", e);
        }
        
        return convertirADTO(repositorioDeClientes.save(nuevoCliente));
    }

    public ClienteDTO guardarNuevoCliente(Cliente cliente) {
        log.info("Guardando nuevo cliente (v2): {}", cliente.getNombreCliente());
        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(LocalDate.now());
        }
        return convertirADTO(repositorioDeClientes.save(cliente));
    }

    public ClienteDTO actualizarCliente(Long idCliente, ClienteDTO datosNuevos) {
        log.info("Actualizando cliente con id: {}", idCliente);
        Cliente clienteExistente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe cliente con id: " + idCliente));
        clienteExistente.setNombreCliente(datosNuevos.getNombreCliente());
        clienteExistente.setApellidoCliente(datosNuevos.getApellidoCliente());
        clienteExistente.setEmailCliente(datosNuevos.getEmailCliente());
        clienteExistente.setTelefonoCliente(datosNuevos.getTelefonoCliente());
        return convertirADTO(repositorioDeClientes.save(clienteExistente));
    }

    public ClienteDTO actualizarCliente(Long idCliente, Cliente datosNuevos) {
        log.info("Actualizando cliente (v2) con id: {}", idCliente);
        Cliente clienteExistente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(()-> new RuntimeException("No existe cliente con id: " + idCliente));
        clienteExistente.setNombreCliente(datosNuevos.getNombreCliente());
        clienteExistente.setApellidoCliente(datosNuevos.getApellidoCliente());
        clienteExistente.setEmailCliente(datosNuevos.getEmailCliente());
        clienteExistente.setTelefonoCliente(datosNuevos.getTelefonoCliente());
        return convertirADTO(repositorioDeClientes.save(clienteExistente));
    }

    public void eliminarCliente(Long idCliente) {
        log.info("Eliminando cliente con id: {}", idCliente);
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
