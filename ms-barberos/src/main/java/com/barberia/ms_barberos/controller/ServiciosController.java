package com.barberia.ms_barberos.controller;

import com.barberia.ms_barberos.dto.ServiciosDTO;
import com.barberia.ms_barberos.model.Servicios;
import com.barberia.ms_barberos.service.ServiciosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios-barbero")
public class ServiciosController {

    @Autowired
    private ServiciosService servicio;

    // trae todos
    @GetMapping
    public List<Servicios> traerTodos() {
        return servicio.traerTodos();
    }

    // trae uno por id
    @GetMapping("/{id}")
    public Servicios traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    // crea uno nuevo
    @PostMapping
    public Servicios crear(@Valid @RequestBody ServiciosDTO datos) {
        return servicio.guardar(datos);
    }

    // actualiza uno existente
    @PutMapping("/{id}")
    public Servicios actualizar(@PathVariable Long id, @Valid @RequestBody ServiciosDTO datos) {
        return servicio.actualizar(id, datos);
    }

    // elimina uno
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
