package com.barberia.ms_pagos.controller;

import com.barberia.ms_pagos.dto.MetodosPagoDTO;
import com.barberia.ms_pagos.model.MetodosPago;
import com.barberia.ms_pagos.service.MetodosPagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodos-pago-sucursal")
public class MetodosPagoController {

    @Autowired
    private MetodosPagoService servicio;

    // trae todos
    @GetMapping
    public List<MetodosPago> traerTodos() {
        return servicio.traerTodos();
    }

    // trae uno por id
    @GetMapping("/{id}")
    public MetodosPago traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    // crea uno nuevo
    @PostMapping
    public MetodosPago crear(@Valid @RequestBody MetodosPagoDTO datos) {
        return servicio.guardar(datos);
    }

    // actualiza uno existente
    @PutMapping("/{id}")
    public MetodosPago actualizar(@PathVariable Long id, @Valid @RequestBody MetodosPagoDTO datos) {
        return servicio.actualizar(id, datos);
    }

    // elimina uno
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
