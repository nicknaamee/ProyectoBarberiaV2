package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.MetodosPagoDTO;
import com.barberia.ms_clientes.model.MetodosPago;
import com.barberia.ms_clientes.service.MetodosPagoService;
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
@RequestMapping("/api/v1/metodos-pago-sucursal")
public class MetodosPagoController {

    @Autowired
    private MetodosPagoService servicio;

    @GetMapping
    public List<MetodosPago> traerTodos() {
        return servicio.traerTodos();
    }

    @GetMapping("/{id}")
    public MetodosPago traerUno(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public MetodosPago crear(@Valid @RequestBody MetodosPagoDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public MetodosPago actualizar(@PathVariable Long id, @Valid @RequestBody MetodosPagoDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
