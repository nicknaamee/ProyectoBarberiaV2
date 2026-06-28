package com.BloqueNico.proyectoBarberia.controller;

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

import com.BloqueNico.proyectoBarberia.dto.BarberoDTO;
import com.BloqueNico.proyectoBarberia.model.Barbero;
import com.BloqueNico.proyectoBarberia.service.BarberoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/barberos")
@Slf4j
public class BarberoController {

    @Autowired
    private BarberoService barberoService;

    @GetMapping
    public ResponseEntity<List<BarberoDTO>> getBarberos() {
        log.info("GET /api/v1/barberos - listando todos los barberos");
        return ResponseEntity.ok(barberoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberoDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/v1/barberos/{} - buscando barbero", id);
        return ResponseEntity.ok(barberoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BarberoDTO> crearBarbero(@Valid @RequestBody Barbero barbero) {
        log.info("POST /api/v1/barberos - creando barbero: {}", barbero.getNombreBarbero());
        BarberoDTO creado = barberoService.guardar(barbero);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody Barbero barbero) {
        log.info("PUT /api/v1/barberos/{} - actualizando barbero", id);
        BarberoDTO actualizado = barberoService.actualizar(id, barbero);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/v1/barberos/{} - eliminando barbero", id);
        barberoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/especialidad/{esp}")
    public ResponseEntity<List<BarberoDTO>> buscarPorEspecialidad(@PathVariable String esp) {
        log.info("GET /api/v1/barberos/especialidad/{} - filtrando por especialidad", esp);
        return ResponseEntity.ok(barberoService.buscarPorEspecialidadBarbero(esp));
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<List<BarberoDTO>> buscarPorEstado(@PathVariable boolean status) {
        log.info("GET /api/v1/barberos/estado/{} - filtrando por estado", status);
        return ResponseEntity.ok(barberoService.buscarPorEstado(status));
    }
}