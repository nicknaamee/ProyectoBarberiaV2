package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ServiciosDTO;
import com.barberia.ms_clientes.model.Servicios;
import com.barberia.ms_clientes.service.ServiciosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios-barbero")
public class ServiciosController {

    @Autowired
    private ServiciosService servicio;

    @GetMapping
    public List<Servicios> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Servicios traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Servicios crear(@Valid @RequestBody ServiciosDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Servicios actualizar(@PathVariable Long id, @Valid @RequestBody ServiciosDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
