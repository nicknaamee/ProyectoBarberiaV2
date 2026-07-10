package com.barberia.ms_barberos.controller;

import com.barberia.ms_barberos.dto.ServicioDTO;
import com.barberia.ms_barberos.model.Servicio;
import com.barberia.ms_barberos.service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicio;

    @GetMapping
    public List<Servicio> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Servicio traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Servicio crear(@Valid @RequestBody ServicioDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Servicio actualizar(@PathVariable Long id, @Valid @RequestBody ServicioDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
