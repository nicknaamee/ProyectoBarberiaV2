package com.barberia.ms_productos.controller;

import com.barberia.ms_productos.dto.ProductosDTO;
import com.barberia.ms_productos.model.Productos;
import com.barberia.ms_productos.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos-sucursal")
public class ProductosController {

    @Autowired
    private ProductosService servicio;

    // trae todos
    @GetMapping
    public List<Productos> traerTodos() {
        return servicio.traerTodos();
    }

    // trae uno por id
    @GetMapping("/{id}")
    public Productos traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    // crea uno nuevo
    @PostMapping
    public Productos crear(@Valid @RequestBody ProductosDTO datos) {
        return servicio.guardar(datos);
    }

    // actualiza uno existente
    @PutMapping("/{id}")
    public Productos actualizar(@PathVariable Long id, @Valid @RequestBody ProductosDTO datos) {
        return servicio.actualizar(id, datos);
    }

    // elimina uno
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
