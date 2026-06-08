package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ResenaDTO;
import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService servicio;

    @GetMapping
    public List<Resena> traerTodas() {
        return servicio.traerTodas();
    }

    @GetMapping("/{id}")
    public Resena traerUna(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Resena crear(@Valid @RequestBody ResenaDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Resena actualizar(@PathVariable Long id, @Valid @RequestBody ResenaDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
