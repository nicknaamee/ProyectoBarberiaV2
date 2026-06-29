package com.barberia.ms_citas.controller.v1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.dto.MetodoPagoDTO;
import com.barberia.ms_citas.service.MetodoPagoService;

import java.util.List;

@RestController("metodoPagoControllerV1")
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService servicio;

    // trae todo
    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> traerTodos() {
        return ResponseEntity.ok(servicio.traerTodos());
    }

    // trae por id
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> traerUno(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.traerPorId(id));
    }

    // crea uno nuevo
    @PostMapping
    public ResponseEntity<MetodoPagoDTO> crear(@Valid @RequestBody MetodoPagoDTO datos) {
        return ResponseEntity.ok(servicio.guardar(datos));
    }

    // actualiza uno existente
    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MetodoPagoDTO datos) {
        return ResponseEntity.ok(servicio.actualizar(id, datos));
    }

    // cambia el estado activo/inactivo
    @PatchMapping("/{id}/estado")
    public ResponseEntity<MetodoPagoDTO> cambiarEstado(@PathVariable Long id, @RequestParam Boolean activo) {
        return ResponseEntity.ok(servicio.cambiarEstado(id, activo));
    }

    // elimina uno
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.ok("Método de pago eliminado correctamente con id: " + id);
    }
}