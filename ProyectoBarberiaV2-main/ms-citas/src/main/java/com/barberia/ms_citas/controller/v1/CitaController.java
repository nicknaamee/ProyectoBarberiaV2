package com.barberia.ms_citas.controller.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.dto.CitaDTO;
import com.barberia.ms_citas.model.Cita;
import com.barberia.ms_citas.service.CitaService;

@RestController("citaControllerV1")
@RequestMapping("/api/v1/citas")
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