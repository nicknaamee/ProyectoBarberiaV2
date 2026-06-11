package com.barberia.ms_clientes.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.service.ResenaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarTodas() {
        List<ResenaDTO> resenas = resenaService.traerTodasLasResenas()
                .stream()
                .map(resenaService::convertirAResenaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> obtenerPorId(@PathVariable Long id) {
        Resena resena = resenaService.traerResenaPorId(id);
        return ResponseEntity.ok(resenaService.convertirAResenaDTO(resena));
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@Valid @RequestBody ResenaDTO resenaDTO) {
        Resena nuevaResena = resenaService.guardarNuevaResena(resenaDTO);
        return new ResponseEntity<>(resenaService.convertirAResenaDTO(nuevaResena), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }
}