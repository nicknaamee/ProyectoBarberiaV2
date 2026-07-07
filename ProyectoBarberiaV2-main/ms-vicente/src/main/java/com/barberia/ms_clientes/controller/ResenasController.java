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

import com.barberia.ms_clientes.dto.ResenasDTO;
import com.barberia.ms_clientes.service.ResenasService;

import jakarta.validation.Valid;

// controller v1 del resumen de reseñas por barbero
@RestController("resenasControllerV1")
@RequestMapping("/api/v1/resenas-resumen")
public class ResenasController {

    @Autowired
    private ResenasService resenasService;

    @GetMapping
    public ResponseEntity<List<ResenasDTO>> listarTodos() {
        return ResponseEntity.ok(resenasService.traerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenasDTO> obtenerUno(@PathVariable Long id) {
        return ResponseEntity.ok(resenasService.traerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResenasDTO> crear(@Valid @RequestBody ResenasDTO resenasDTO) {
        return new ResponseEntity<>(resenasService.crear(resenasDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenasDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ResenasDTO resenasDTO) {
        return ResponseEntity.ok(resenasService.actualizar(id, resenasDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resenasService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
