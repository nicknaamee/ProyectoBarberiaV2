package com.barberia.ms_pagos.controller;

import com.barberia.ms_pagos.dto.ReporteDTO;
import com.barberia.ms_pagos.model.Reporte;
import com.barberia.ms_pagos.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService servicio;

    @GetMapping
    public List<Reporte> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Reporte traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Reporte crear(@Valid @RequestBody ReporteDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Reporte actualizar(@PathVariable Long id, @Valid @RequestBody ReporteDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
