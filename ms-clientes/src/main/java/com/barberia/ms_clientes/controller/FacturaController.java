package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.FacturaDTO;
import com.barberia.ms_clientes.model.Factura;
import com.barberia.ms_clientes.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService servicio;

    @GetMapping
    public List<Factura> traerTodas() {
        return servicio.traerTodas();
    }

    @GetMapping("/{id}")
    public Factura traerUna(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Factura crear(@Valid @RequestBody FacturaDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Factura actualizar(@PathVariable Long id, @Valid @RequestBody FacturaDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
