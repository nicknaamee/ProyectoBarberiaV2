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

import com.BloqueNico.proyectoBarberia.dto.ReporteDTO;
import com.BloqueNico.proyectoBarberia.service.ReporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reportes") 
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> listarReportes() {
        List<ReporteDTO> reportes = reporteService.traerTodosLosReportes();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> obtenerPorId(@PathVariable Long id) {
        ReporteDTO reporteDTO = reporteService.traerReportePorId(id);
        return ResponseEntity.ok(reporteDTO);
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> crearReporte(@Valid @RequestBody ReporteDTO reporteDTO) {
        ReporteDTO nuevoReporteDTO = reporteService.guardarNuevoReporte(reporteDTO);
        return new ResponseEntity<>(nuevoReporteDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> actualizarReporte(@PathVariable Long id, @Valid @RequestBody ReporteDTO reporteDTO) {
        ReporteDTO actualizadoDTO = reporteService.actualizarReporte(id, reporteDTO);
        return ResponseEntity.ok(actualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        reporteService.eliminarReporte(id);
        return ResponseEntity.noContent().build();
    }
}