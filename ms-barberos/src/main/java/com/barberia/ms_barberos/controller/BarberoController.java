package com.barberia.ms_barberos.controller;

import com.barberia.ms_barberos.dto.BarberoDTO;
import com.barberia.ms_barberos.model.Barbero;
import com.barberia.ms_barberos.service.BarberoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barberos")
public class BarberoController {

    @Autowired
    private BarberoService servicio;

    @GetMapping
    public List<Barbero> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Barbero traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Barbero crear(@Valid @RequestBody BarberoDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Barbero actualizar(@PathVariable Long id, @Valid @RequestBody BarberoDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
