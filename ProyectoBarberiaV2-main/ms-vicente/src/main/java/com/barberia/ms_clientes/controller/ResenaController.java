package com.barberia.ms_clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.ms_clientes.dto.ResenaDTO;
import com.barberia.ms_clientes.service.ResenaService;

import jakarta.validation.Valid;

// controller v1 de reseñas
@RestController("resenaControllerV1")
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarResenas() {
        return ResponseEntity.ok(resenaService.traerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> obtenerResena(@PathVariable Long id) {
        return ResponseEntity.ok(resenaService.traerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@Valid @RequestBody ResenaDTO resenaDTO) {
        return new ResponseEntity<>(resenaService.crear(resenaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
