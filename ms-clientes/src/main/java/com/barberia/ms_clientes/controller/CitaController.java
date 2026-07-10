package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.CitaDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {

    @Autowired
    private CitaService servicio;

    @GetMapping
    public List<Cita> traerTodas() {
        return servicio.traerTodas();
    }

    @GetMapping("/{id}")
    public Cita traerUna(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Cita crear(@Valid @RequestBody CitaDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Cita actualizar(@PathVariable Long id, @Valid @RequestBody CitaDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
