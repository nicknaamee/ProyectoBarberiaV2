package com.BloqueNico.proyectoBarberia.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BloqueNico.proyectoBarberia.dto.ClienteDTO;
import com.BloqueNico.proyectoBarberia.model.Cliente;
import com.BloqueNico.proyectoBarberia.assemblers.ClienteModelAssembler;
import com.BloqueNico.proyectoBarberia.service.ClienteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("clienteControllerV2")
@RequestMapping("/api/v2/clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService servicioDeClientes;

    @Autowired
    private ClienteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO>>> traerTodos() {
        log.info("GET /api/v2/clientes - trayendo todos los clientes con HATEOAS");
        List<EntityModel<ClienteDTO>> clientes = servicioDeClientes.traerTodosLosClientes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                clientes,
                linkTo(methodOn(ClienteController.class).traerTodos()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ClienteDTO>> traerUno(@PathVariable Long id) {
        log.info("GET /api/v2/clientes/{} - buscando cliente con HATEOAS", id);
        try {
            ClienteDTO dto = servicioDeClientes.traerClientePorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ClienteDTO>> crear(@Valid @RequestBody Cliente cliente) {
        log.info("POST /api/v2/clientes - creando cliente con HATEOAS");
        try {
            ClienteDTO clienteCreado = servicioDeClientes.guardarNuevoCliente(cliente);
            return ResponseEntity
                    .created(linkTo(methodOn(ClienteController.class).traerUno(clienteCreado.getIdCliente())).toUri())
                    .body(assembler.toModel(clienteCreado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ClienteDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {
        log.info("PUT /api/v2/clientes/{} - actualizando cliente con HATEOAS", id);
        try {
            ClienteDTO clienteActualizado = servicioDeClientes.actualizarCliente(id, cliente);
            if (clienteActualizado == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(clienteActualizado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v2/clientes/{} - eliminando cliente con HATEOAS", id);
        try {
            servicioDeClientes.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}