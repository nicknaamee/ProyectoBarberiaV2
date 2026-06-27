package com.barberia.ms_clientes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_clientes.dto.CitaDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listarTodas() {
        return ResponseEntity.ok(citaService.obtenerTodos());
    }

    @PostMapping("/agendar")
    public ResponseEntity<CitaDTO> agendar(@RequestBody Cita nuevaCita) {
        return ResponseEntity.ok(citaService.agendarCita(nuevaCita));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita datosNuevos) {
        return ResponseEntity.ok(citaService.actualizarCitas(id, datosNuevos));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaDTO> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(citaService.cambiarEstadoCita(id, nuevoEstado));
    }

    @GetMapping("/barbero/{idBarbero}")
    public ResponseEntity<List<CitaDTO>> listarPorBarbero(@PathVariable Long idBarbero) {
        return ResponseEntity.ok(citaService.buscarPorBarbero(idBarbero));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<CitaDTO>> listarPorEstado(@RequestParam String estado) {
        return ResponseEntity.ok(citaService.buscarPorEstadoCita(estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.eliminar(id));
    }
}