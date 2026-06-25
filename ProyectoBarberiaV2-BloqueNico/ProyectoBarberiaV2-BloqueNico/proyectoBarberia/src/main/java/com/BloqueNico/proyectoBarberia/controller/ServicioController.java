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

import com.BloqueNico.proyectoBarberia.dto.ServicioDTO;
import com.BloqueNico.proyectoBarberia.service.ServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/servicios") 
public class ServicioController {

    @Autowired 
    private ServicioService servicioService;
    @GetMapping
    public ResponseEntity<List<ServicioDTO>> listarServicios() {
        List<ServicioDTO> servicios = servicioService.traerTodosLosServicios();
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerServicio(@PathVariable Long id) {
        ServicioDTO servicioDTO = servicioService.traerServicioPorId(id);
        return ResponseEntity.ok(servicioDTO);
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> crearServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        ServicioDTO nuevoServicioDTO = servicioService.guardarNuevoServicio(servicioDTO);
        return new ResponseEntity<>(nuevoServicioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizarServicio(@PathVariable Long id, @Valid @RequestBody ServicioDTO servicioDTO) {
        ServicioDTO actualizadoDTO = servicioService.actualizarServicio(id, servicioDTO);
        return ResponseEntity.ok(actualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}