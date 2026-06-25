package com.BloqueNico.proyectoBarberia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.ClienteDTO;
import com.BloqueNico.proyectoBarberia.model.Cliente;
import com.BloqueNico.proyectoBarberia.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteService {

    @Autowired 
    private ClienteRepository repositorioDeClientes;

    @Autowired 
    private ClienteValidaciones validaciones;

    public List<ClienteDTO> traerTodosLosClientes() {
        log.info("Buscando todos los clientes en la BD");
        return repositorioDeClientes.findAll().stream()
                .map(validaciones::convertirADTO)
                .toList();
    }

    public ClienteDTO traerClientePorId(Long idCliente) {
        log.info("Buscando cliente con id: {}", idCliente);
        Cliente cliente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("No existe cliente con id: " + idCliente));
        return validaciones.convertirADTO(cliente);
    }

    public ClienteDTO guardarNuevoCliente(ClienteDTO datosDelFormulario) {
        log.info("Guardando nuevo cliente: {}", datosDelFormulario.getNombreCliente());
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombreCliente(datosDelFormulario.getNombreCliente());
        nuevoCliente.setApellidoCliente(datosDelFormulario.getApellidoCliente());
        nuevoCliente.setEmailCliente(datosDelFormulario.getEmailCliente());
        nuevoCliente.setTelefonoCliente(datosDelFormulario.getTelefonoCliente());
        nuevoCliente.setFechaRegistro(LocalDate.now());

        if (!validaciones.validarCliente(nuevoCliente)) {
            throw new RuntimeException("Los datos del cliente infringen las reglas de negocio o exceden el largo permitido");
        }
        return validaciones.convertirADTO(repositorioDeClientes.save(nuevoCliente));
    }

    public ClienteDTO actualizarCliente(Long idCliente, ClienteDTO datosNuevos) {
        log.info("Actualizando cliente con id: {}", idCliente);
        
        Cliente clienteExistente = repositorioDeClientes.findById(idCliente)
            .orElseThrow(() -> new RuntimeException("No existe cliente con id: " + idCliente));
            
        clienteExistente.setNombreCliente(datosNuevos.getNombreCliente());
        clienteExistente.setApellidoCliente(datosNuevos.getApellidoCliente());
        clienteExistente.setEmailCliente(datosNuevos.getEmailCliente());
        clienteExistente.setTelefonoCliente(datosNuevos.getTelefonoCliente());

        if (!validaciones.validarCliente(clienteExistente)) {
            throw new RuntimeException("La actualización del cliente contiene datos inválidos o nombres muy largos");
        }
        return validaciones.convertirADTO(repositorioDeClientes.save(clienteExistente));
    }

    public void eliminarCliente(Long idCliente) {
        log.info("Eliminando cliente con id: {}", idCliente);
        if (!repositorioDeClientes.existsById(idCliente)) {
            throw new RuntimeException("No existe cliente con ese Id");
        }
        repositorioDeClientes.deleteById(idCliente);
    }
}