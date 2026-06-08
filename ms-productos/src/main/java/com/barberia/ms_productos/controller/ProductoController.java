package com.barberia.ms_productos.controller;

import com.barberia.ms_productos.dto.ProductoDTO;
import com.barberia.ms_productos.model.Producto;
import com.barberia.ms_productos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService servicio;

    @GetMapping
    public List<Producto> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Producto traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Producto crear(@Valid @RequestBody ProductoDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
