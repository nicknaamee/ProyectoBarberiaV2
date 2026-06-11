package com.barberia.ms_clientes.controller;

import com.barberia.ms_clientes.dto.FacturaDTO;
import com.barberia.ms_clientes.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/emitir")
    public ResponseEntity<FacturaDTO> emitir(@RequestParam Long idCita,
                                            @RequestParam String metodo) {
        return ResponseEntity.ok(facturaService.generarFactura(idCita, metodo));
    }
}