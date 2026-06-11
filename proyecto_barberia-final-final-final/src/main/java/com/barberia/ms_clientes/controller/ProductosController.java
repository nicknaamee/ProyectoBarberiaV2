package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ProductosDTO;
import com.barberia.ms_clientes.model.Productos;
import com.barberia.ms_clientes.service.ProductosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos-sucursal")
public class ProductosController {

    @Autowired
    private ProductosService servicio;

    @GetMapping
    public List<Productos> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Productos traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Productos crear(@Valid @RequestBody ProductosDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Productos actualizar(@PathVariable Long id, @Valid @RequestBody ProductosDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
