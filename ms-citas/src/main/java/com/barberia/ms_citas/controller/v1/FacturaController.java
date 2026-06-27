package com.barberia.ms_citas.controller.v1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.dto.FacturaDTO;
import com.barberia.ms_citas.service.FacturaService;

@RestController("facturaControllerV1")
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> listarTodas() {
        return ResponseEntity.ok(facturaService.obtenerTodas());
    }

    @PostMapping("/emitir")
    public ResponseEntity<FacturaDTO> emitir(@RequestParam Long idCita, @RequestParam String metodo) {
        return ResponseEntity.ok(facturaService.generarFactura(idCita, metodo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerPorId(id));
    }

    @PatchMapping("/{id}/metodo-pago")
    public ResponseEntity<FacturaDTO> cambiarMetodoPago(@PathVariable Long id, @RequestParam String nuevoMetodo) {
        return ResponseEntity.ok(facturaService.cambiarMetodoPago(id, nuevoMetodo));
    }
}