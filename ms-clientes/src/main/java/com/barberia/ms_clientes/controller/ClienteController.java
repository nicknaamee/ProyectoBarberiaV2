package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ClienteDTO;
import com.barberia.ms_clientes.model.Cliente;
import com.barberia.ms_clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService servicioDeClientes;

    // GET /api/v1/clientes
    @GetMapping
    public List<Cliente> traerTodos() {
        return servicioDeClientes.traerTodosLosClientes();
    }

    // GET /api/v1/clientes/1
    @GetMapping("/{id}")
    public Cliente traerUno(@PathVariable Long id) {
        return servicioDeClientes.traerClientePorId(id);
    }

    // POST /api/v1/clientes
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente crear(@Valid @RequestBody ClienteDTO formulario) {
        return servicioDeClientes.guardarNuevoCliente(formulario);
    }

    // PUT /api/v1/clientes/1
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO datosNuevos) {
        return servicioDeClientes.actualizarCliente(id, datosNuevos);
    }

    // DELETE /api/v1/clientes/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        servicioDeClientes.eliminarCliente(id);
    }
}