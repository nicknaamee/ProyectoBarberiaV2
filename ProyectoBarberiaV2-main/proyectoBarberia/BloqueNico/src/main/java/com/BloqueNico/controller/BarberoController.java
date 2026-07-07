package com.BloqueNico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import com.BloqueNico.dto.BarberoDTO;
import com.BloqueNico.model.Barbero;
import com.BloqueNico.service.BarberoService;

@RestController
@RequestMapping("/api/v1/barberos")
public class BarberoController {

    @Autowired
    private BarberoService barberoService;

    @GetMapping
    public ResponseEntity<List<BarberoDTO>> getBarberos() {
        return ResponseEntity.ok(barberoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(barberoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Barbero> crearBarbero(@Valid @RequestBody Barbero barbero) {
        return ResponseEntity.status(HttpStatus.CREATED).body(barberoService.guardarBarbero(barbero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbero> actualizar(@PathVariable Long id, @Valid @RequestBody Barbero barbero) {
        return ResponseEntity.ok(barberoService.actualizarBarberos(id, barbero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(barberoService.eliminar(id));
    }

    @GetMapping("/especialidad/{esp}")
    public ResponseEntity<List<BarberoDTO>> buscarPorEspecialidad(@PathVariable String esp) {
        return ResponseEntity.ok(barberoService.buscarPorEspecialidadBarbero(esp));
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<List<BarberoDTO>> buscarPorEstado(@PathVariable boolean status) {
        return ResponseEntity.ok(barberoService.buscarPorEstado(status));
    }
}