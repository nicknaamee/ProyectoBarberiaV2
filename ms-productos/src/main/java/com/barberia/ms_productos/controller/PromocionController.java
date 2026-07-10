package com.barberia.ms_productos.controller;

import com.barberia.ms_productos.dto.PromocionDTO;
import com.barberia.ms_productos.model.Promocion;
import com.barberia.ms_productos.service.PromocionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promociones")
public class PromocionController {

    @Autowired
    private PromocionService servicio;

    @GetMapping
    public List<Promocion> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public Promocion traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Promocion crear(@Valid @RequestBody PromocionDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Promocion actualizar(@PathVariable Long id, @Valid @RequestBody PromocionDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
