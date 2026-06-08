package com.barberia.ms_barberos.controller;

import com.barberia.ms_barberos.dto.SucursalDTO;
import com.barberia.ms_barberos.model.Sucursal;
import com.barberia.ms_barberos.service.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService servicio;

    @GetMapping
    public List<Sucursal> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Sucursal traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Sucursal crear(@Valid @RequestBody SucursalDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Sucursal actualizar(@PathVariable Long id, @Valid @RequestBody SucursalDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
