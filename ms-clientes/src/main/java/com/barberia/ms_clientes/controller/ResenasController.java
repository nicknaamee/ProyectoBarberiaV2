package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ResenasDTO;
import com.barberia.ms_clientes.model.Resenas;
import com.barberia.ms_clientes.service.ResenasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resenas-resumen")
public class ResenasController {

    @Autowired
    private ResenasService servicio;

    // trae todas
    @GetMapping
    public List<Resenas> traerTodas() {
        return servicio.traerTodas();
    }

    // trae una por id
    @GetMapping("/{id}")
    public Resenas traerUna(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    // crea una nueva
    @PostMapping
    public Resenas crear(@Valid @RequestBody ResenasDTO datos) {
        return servicio.guardar(datos);
    }

    // actualiza una existente
    @PutMapping("/{id}")
    public Resenas actualizar(@PathVariable Long id, @Valid @RequestBody ResenasDTO datos) {
        return servicio.actualizar(id, datos);
    }

    // elimina una
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
