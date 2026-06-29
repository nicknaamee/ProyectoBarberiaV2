package com.barberia.ms_citas.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.assembler.CitaModelAssembler;
import com.barberia.ms_citas.dto.CitaDTO;
import com.barberia.ms_citas.model.Cita;
import com.barberia.ms_citas.service.CitaService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("citaControllerV2")
@RequestMapping("/api/v2/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private CitaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<CitaDTO>>> listarTodas() {
        List<EntityModel<CitaDTO>> citas = citaService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                citas,
                linkTo(methodOn(CitaController.class).listarTodas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<CitaDTO>> porId(@PathVariable Long id) {
        try {
            CitaDTO dto = citaService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/agendar", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<CitaDTO>> agendar(@Valid @RequestBody Cita nuevaCita) {
        try {
            CitaDTO newCita = citaService.agendarCita(nuevaCita);
            return ResponseEntity
                    .created(linkTo(methodOn(CitaController.class).porId(newCita.getIdCita())).toUri())
                    .body(assembler.toModel(newCita));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/actualizar/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<CitaDTO>> actualizar(@PathVariable Long id, @RequestBody Cita datosNuevos) {
        try {
            citaService.actualizarCitas(id, datosNuevos);
            CitaDTO actualizada = citaService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(actualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/{id}/estado", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<CitaDTO>> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        try {
            CitaDTO actualizada = citaService.cambiarEstadoCita(id, nuevoEstado);
            return ResponseEntity.ok(assembler.toModel(actualizada));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/barbero/{idBarbero}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<CitaDTO>>> listarPorBarbero(@PathVariable Long idBarbero) {
        List<EntityModel<CitaDTO>> citas = citaService.buscarPorBarbero(idBarbero).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                citas,
                linkTo(methodOn(CitaController.class).listarPorBarbero(idBarbero)).withSelfRel()
        ));
    }

    @GetMapping(value = "/estado", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<CitaDTO>>> listarPorEstado(@RequestParam String estado) {
        List<EntityModel<CitaDTO>> citas = citaService.buscarPorEstadoCita(estado).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                citas,
                linkTo(methodOn(CitaController.class).listarPorEstado(estado)).withSelfRel()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            citaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
