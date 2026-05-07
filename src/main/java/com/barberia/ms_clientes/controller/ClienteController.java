package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ClienteDTO;
import com.barberia.ms_clientes.model.Cliente;
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

    // GET /api/v1/clientes → trae todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> traerTodos() {
        log.info("GET /api/v1/clientes - trayendo todos los clientes");
        return ResponseEntity.ok(servicioDeClientes.traerTodosLosClientes());
    }

    // GET /api/v1/clientes/1 → trae el cliente cuyo id es 1
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> traerUno(@PathVariable Long id) {
        log.info("GET /api/v1/clientes/{} - buscando cliente", id);
        return ResponseEntity.ok(servicioDeClientes.traerClientePorId(id));
    }

    // POST /api/v1/clientes → crea un cliente nuevo
    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody ClienteDTO formulario) {
        log.info("POST /api/v1/clientes - creando cliente: {}", formulario.getNombreDelCliente());
        Cliente clienteCreado = servicioDeClientes.guardarNuevoCliente(formulario);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    // PUT /api/v1/clientes/1 → actualiza el cliente cuyo id es 1
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO datosNuevos) {
        log.info("PUT /api/v1/clientes/{} - actualizando cliente", id);
        return ResponseEntity.ok(servicioDeClientes.actualizarCliente(id, datosNuevos));
    }

    // DELETE /api/v1/clientes/1 → elimina el cliente cuyo id es 1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v1/clientes/{} - eliminando cliente", id);
        servicioDeClientes.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
