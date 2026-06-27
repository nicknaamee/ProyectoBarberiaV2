package com.BloqueNico.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BloqueNico.dto.ReporteDTO;
import com.BloqueNico.model.Reporte;
import com.BloqueNico.assemblers.ReporteModelAssembler;
import com.BloqueNico.service.ReporteService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("reporteControllerV2")
@RequestMapping("/api/v2/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ReporteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ReporteDTO>>> listarReportes() {
        List<EntityModel<ReporteDTO>> reportes = reporteService.traerTodosLosReportes()
                .stream()
                .map(reporteService::convertirAReporteDTO)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (reportes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                reportes,
                linkTo(methodOn(ReporteController.class).listarReportes()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ReporteDTO>> obtenerPorId(@PathVariable Long id) {
        try {
            Reporte reporte = reporteService.traerReportePorId(id);
            if (reporte == null) {
                return ResponseEntity.notFound().build();
            }
            ReporteDTO dto = reporteService.convertirAReporteDTO(reporte);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ReporteDTO>> crearReporte(@Valid @RequestBody ReporteDTO reporteDTO) {
        try {
            Reporte nuevoReporte = reporteService.guardarNuevoReporte(reporteDTO);
            ReporteDTO dto = reporteService.convertirAReporteDTO(nuevoReporte);
            
            return ResponseEntity
                    .created(linkTo(methodOn(ReporteController.class).obtenerPorId(dto.getIdDelReporte())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ReporteDTO>> actualizarReporte(
            @PathVariable Long id, 
            @Valid @RequestBody ReporteDTO reporteDTO) {
        try {
            Reporte actualizado = reporteService.actualizarReporte(id, reporteDTO);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            ReporteDTO dto = reporteService.convertirAReporteDTO(actualizado);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        try {
            reporteService.eliminarReporte(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}