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

import com.barberia.ms_clientes.dto.ServicioDTO;
import com.barberia.ms_clientes.service.ServicioService;

import jakarta.validation.Valid;

// controller v1 de servicios (cortes, barba, etc)
@RestController("servicioControllerV1")
@RequestMapping("/api/v1/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> listarServicios() {
        return ResponseEntity.ok(servicioService.traerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerServicio(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.traerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> crearServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        return new ResponseEntity<>(servicioService.crear(servicioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizarServicio(@PathVariable Long id, @Valid @RequestBody ServicioDTO servicioDTO) {
        return ResponseEntity.ok(servicioService.actualizar(id, servicioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
