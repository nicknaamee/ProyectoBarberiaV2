package com.barberia.ms_productos.controller;

import com.barberia.ms_productos.dto.ProveedorDTO;
import com.barberia.ms_productos.model.Proveedor;
import com.barberia.ms_productos.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService servicio;

    @GetMapping
    public List<Proveedor> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Proveedor traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Proveedor crear(@Valid @RequestBody ProveedorDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Proveedor actualizar(@PathVariable Long id, @Valid @RequestBody ProveedorDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
