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
import com.BloqueNico.proyectoBarberia.service.BarberoService;

import jakarta.validation.Valid;

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
    public ResponseEntity<BarberoDTO> crearBarbero(@Valid @RequestBody BarberoDTO barberoDTO) {
        BarberoDTO nuevoBarbero = barberoService.guardarBarbero(barberoDTO);
        return new ResponseEntity<>(nuevoBarbero, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody BarberoDTO barberoDTO) {
        BarberoDTO actualizado = barberoService.actualizarBarberos(id, barberoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        barberoService.eliminar(id);
        return ResponseEntity.noContent().build();
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