package com.barberia.ms_citas.controller.v2;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.assembler.MetodosPagoModelAssembler;
import com.barberia.ms_citas.dto.MetodosPagoDTO;
import com.barberia.ms_citas.service.MetodosPagoService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("metodosPagoControllerV2")
@RequestMapping("/api/v2/metodos-pago-sucursal")
public class MetodosPagoController {

    @Autowired
    private MetodosPagoService servicio;

    @Autowired
    private MetodosPagoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<MetodosPagoDTO>>> traerTodos() {
        List<EntityModel<MetodosPagoDTO>> metodos = servicio.traerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (metodos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                metodos,
                linkTo(methodOn(MetodosPagoController.class).traerTodos()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodosPagoDTO>> traerUno(@PathVariable Long id) {
        try {
            MetodosPagoDTO dto = servicio.traerPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodosPagoDTO>> crear(@Valid @RequestBody MetodosPagoDTO datos) {
        try {
            MetodosPagoDTO dto = servicio.guardar(datos);
            return ResponseEntity
                    .created(linkTo(methodOn(MetodosPagoController.class).traerUno(dto.getId())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodosPagoDTO>> actualizar(@PathVariable Long id, @Valid @RequestBody MetodosPagoDTO datos) {
        try {
            MetodosPagoDTO dto = servicio.actualizar(id, datos);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/{id}/estado", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MetodosPagoDTO>> cambiarEstado(@PathVariable Long id, @RequestParam Boolean activo) {
        try {
            MetodosPagoDTO dto = servicio.cambiarEstado(id, activo);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
