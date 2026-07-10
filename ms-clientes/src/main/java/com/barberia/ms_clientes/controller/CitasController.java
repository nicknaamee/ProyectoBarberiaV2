package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.CitasDTO;
import com.barberia.ms_clientes.model.Citas;
import com.barberia.ms_clientes.service.CitasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas-resumen")
public class CitasController {

    @Autowired
    private CitasService servicio;

    // trae todas
    @GetMapping
    public List<Citas> traerTodas() {
        return servicio.traerTodas();
    }

    // trae una por id
    @GetMapping("/{id}")
    public Citas traerUna(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    // crea una nueva
    @PostMapping
    public Citas crear(@Valid @RequestBody CitasDTO datos) {
        return servicio.guardar(datos);
    }

    // actualiza una existente
    @PutMapping("/{id}")
    public Citas actualizar(@PathVariable Long id, @Valid @RequestBody CitasDTO datos) {
        return servicio.actualizar(id, datos);
    }

    // elimina una
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
