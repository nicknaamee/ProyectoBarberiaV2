package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ClienteDTO;
import com.barberia.ms_clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Slf4j

public class ClienteController {
    @Autowired
    private ClienteService servicioDeClientes;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> traerTodos() {
        log.info("GET /api/v1/clientes - trayendo todos los clientes");
        return ResponseEntity.ok(servicioDeClientes.traerTodosLosClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> traerUno(@PathVariable Long id) {
        log.info("GET /api/v1/clientes/{} - buscando cliente", id);
        return ResponseEntity.ok(servicioDeClientes.traerClientePorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO formulario) {
        log.info("POST /api/v1/clientes - creando cliente: {}", formulario.getNombreCliente());
        ClienteDTO clienteCreado = servicioDeClientes.guardarNuevoCliente(formulario);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO datosNuevos) {
        log.info("PUT /api/v1/clientes/{} - actualizando cliente", id);
        return ResponseEntity.ok(servicioDeClientes.actualizarCliente(id, datosNuevos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v1/clientes/{} - eliminando cliente", id);
        servicioDeClientes.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
