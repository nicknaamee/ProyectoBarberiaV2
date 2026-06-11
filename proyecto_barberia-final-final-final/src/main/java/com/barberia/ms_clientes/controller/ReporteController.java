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

import com.barberia.ms_clientes.dto.ReporteDTO;
import com.barberia.ms_clientes.model.Reporte;
import com.barberia.ms_clientes.service.ReporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;


    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listarReportes() {
        List<ReporteDTO> reportes = reporteService.traerTodosLosReportes()
                .stream()
                .map(reporteService::convertirAReporteDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtenerPorId(@PathVariable Long id) {
        Reporte reporte = reporteService.traerReportePorId(id);
        return ResponseEntity.ok(reporteService.convertirAReporteDTO(reporte));
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> crearReporte(@Valid @RequestBody ReporteDTO reporteDTO) {
        Reporte nuevoReporte = reporteService.guardarNuevoReporte(reporteDTO);
        return new ResponseEntity<>(reporteService.convertirAReporteDTO(nuevoReporte), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizarReporte(@PathVariable Long id, @Valid @RequestBody ReporteDTO reporteDTO) {
        Reporte actualizado = reporteService.actualizarReporte(id, reporteDTO);
        return ResponseEntity.ok(reporteService.convertirAReporteDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}