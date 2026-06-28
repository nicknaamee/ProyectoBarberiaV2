package com.BloqueNico.proyectoBarberia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.BloqueNico.proyectoBarberia.dto.ClienteDTO;
import com.BloqueNico.proyectoBarberia.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Slf4j
@Tag(name = "Cliente", description = "API de operaciones para la entidad Cliente")
public class ClienteController {
    @Autowired
    private ClienteService servicioDeClientes;

    @Operation(summary = "Obtener todos los clientes", description = "Retorna una lista con todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> traerTodos() {
        log.info("GET /api/v1/clientes - trayendo todos los clientes");
        return ResponseEntity.ok(servicioDeClientes.traerTodosLosClientes());
    }

    @Operation(summary = "Obtener un cliente por su ID", description = "Retorna un cliente específico según su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> traerUno(@PathVariable Long id) {
        log.info("GET /api/v1/clientes/{} - buscando cliente", id);
        return ResponseEntity.ok(servicioDeClientes.traerClientePorId(id));
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Guarda un cliente en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta (validación fallida)")
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO formulario) {
        log.info("POST /api/v1/clientes - creando cliente: {}", formulario.getNombreCliente());
        ClienteDTO clienteCreado = servicioDeClientes.guardarNuevoCliente(formulario);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @Operation(summary = "Actualizar un cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO datosNuevos) {
        log.info("PUT /api/v1/clientes/{} - actualizando cliente", id);
        return ResponseEntity.ok(servicioDeClientes.actualizarCliente(id, datosNuevos));
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina físicamente un cliente de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado sin contenido de respuesta"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v1/clientes/{} - eliminando cliente", id);
        servicioDeClientes.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
