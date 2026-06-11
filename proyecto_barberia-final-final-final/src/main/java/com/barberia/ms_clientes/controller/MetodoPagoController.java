package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.MetodoPagoDTO;
import com.barberia.ms_clientes.model.MetodoPago;
import com.barberia.ms_clientes.service.MetodoPagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService servicio;

    // trae todos
    @GetMapping
    public List<MetodoPago> traerTodos() {
        return servicio.traerTodos();
    }

    // trae uno por id
    @GetMapping("/{id}")
    public MetodoPago traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    // crea uno nuevo
    @PostMapping
    public MetodoPago crear(@Valid @RequestBody MetodoPagoDTO datos) {
        return servicio.guardar(datos);
    }

    // actualiza uno existente
    @PutMapping("/{id}")
    public MetodoPago actualizar(@PathVariable Long id, @Valid @RequestBody MetodoPagoDTO datos) {
        return servicio.actualizar(id, datos);
    }

    // elimina uno
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
