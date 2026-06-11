package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.ResenasDTO;
import com.barberia.ms_clientes.model.Resenas;
import com.barberia.ms_clientes.service.ResenasService;
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
@RequestMapping("/api/v1/resenas-resumen")
public class ResenasController {

    @Autowired
    private ResenasService servicio;

    @GetMapping
    public List<Resenas> traerTodas() {
        return servicio.traerTodas();
    }

    @GetMapping("/{id}")
    public Resenas traerUna(@PathVariable Long id) {
        return servicio.traerPorId(id);
    }

    @PostMapping
    public Resenas crear(@Valid @RequestBody ResenasDTO datos) {
        return servicio.guardar(datos);
    }

    @PutMapping("/{id}")
    public Resenas actualizar(@PathVariable Long id, @Valid @RequestBody ResenasDTO datos) {
        return servicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
