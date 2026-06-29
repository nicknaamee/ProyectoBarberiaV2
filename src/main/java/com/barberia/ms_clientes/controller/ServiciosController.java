package com.barberia.ms_clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.ms_clientes.dto.ServiciosDTO;
import com.barberia.ms_clientes.service.ServiciosService;

import jakarta.validation.Valid;

// controller v1 de servicios que puede hacer cada barbero
@RestController("serviciosControllerV1")
@RequestMapping("/api/v1/servicios-barbero")
public class ServiciosController {

    @Autowired
    private ServiciosService serviciosService;

    @GetMapping
    public ResponseEntity<List<ServiciosDTO>> listarTodos() {
        return ResponseEntity.ok(serviciosService.traerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiciosDTO> obtenerUno(@PathVariable Long id) {
        return ResponseEntity.ok(serviciosService.traerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ServiciosDTO> crear(@Valid @RequestBody ServiciosDTO serviciosDTO) {
        return new ResponseEntity<>(serviciosService.crear(serviciosDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiciosDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ServiciosDTO serviciosDTO) {
        return ResponseEntity.ok(serviciosService.actualizar(id, serviciosDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        serviciosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
