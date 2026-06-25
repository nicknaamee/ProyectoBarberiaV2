package com.BloqueNico.proyectoBarberia.controller;

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

import com.BloqueNico.proyectoBarberia.dto.ResenaDTO;
import com.BloqueNico.proyectoBarberia.service.ResenaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired 
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listarTodas() {
        List<ResenaDTO> resenas = resenaService.traerTodasLasResenas();
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> obtenerPorId(@PathVariable Long id) {
        ResenaDTO resenaDTO = resenaService.traerResenaPorId(id);
        return ResponseEntity.ok(resenaDTO);
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@Valid @RequestBody ResenaDTO resenaDTO) {
        ResenaDTO nuevaResenaDTO = resenaService.guardarNuevaResena(resenaDTO);
        return new ResponseEntity<>(nuevaResenaDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }
}