package com.barberia.ms_citas.controller.v1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.dto.MetodosPagoDTO;
import com.barberia.ms_citas.service.MetodosPagoService;

import java.util.List;

@RestController("metodosPagoControllerV1")
@RequestMapping("/api/v1/metodos-pago-sucursal")
public class MetodosPagoController {

    @Autowired
    private MetodosPagoService servicio;

    // trae todos
    @GetMapping
    public ResponseEntity<List<MetodosPagoDTO>> traerTodos() {
        return ResponseEntity.ok(servicio.traerTodos());
    }

    // trae uno por id
    @GetMapping("/{id}")
    public ResponseEntity<MetodosPagoDTO> traerUno(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.traerPorId(id));
    }

    // crea uno nuevo
    @PostMapping
    public ResponseEntity<MetodosPagoDTO> crear(@Valid @RequestBody MetodosPagoDTO datos) {
        return ResponseEntity.ok(servicio.guardar(datos));
    }

    // actualiza uno existente
    @PutMapping("/{id}")
    public ResponseEntity<MetodosPagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MetodosPagoDTO datos) {
        return ResponseEntity.ok(servicio.actualizar(id, datos));
    }

    // cambia el estado activo/inactivo
    @PatchMapping("/{id}/estado")
    public ResponseEntity<MetodosPagoDTO> cambiarEstado(@PathVariable Long id, @RequestParam Boolean activo) {
        return ResponseEntity.ok(servicio.cambiarEstado(id, activo));
    }

    // elimina uno
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.ok("Método de pago de sucursal eliminado correctamente con id: " + id);
    }
}