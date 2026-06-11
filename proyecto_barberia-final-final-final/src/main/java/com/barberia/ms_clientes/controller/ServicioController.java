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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.ms_clientes.dto.ServicioDTO;
import com.barberia.ms_clientes.model.Servicio;
import com.barberia.ms_clientes.service.ServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> listarServicios() {
        List<ServicioDTO> servicios = servicioService.traerTodosLosServicios()
                .stream()
                .map(servicioService::convertirAServicioDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerServicio(@PathVariable Long id) {
        Servicio servicio = servicioService.traerServicioPorId(id);
        return ResponseEntity.ok(servicioService.convertirAServicioDTO(servicio));
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> crearServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        Servicio nuevoServicio = servicioService.guardarNuevoServicio(servicioDTO);
        return new ResponseEntity<>(servicioService.convertirAServicioDTO(nuevoServicio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizarServicio(@PathVariable Long id, @Valid @RequestBody ServicioDTO servicioDTO) {
        Servicio actualizado = servicioService.actualizarServicio(id, servicioDTO);
        return ResponseEntity.ok(servicioService.convertirAServicioDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}