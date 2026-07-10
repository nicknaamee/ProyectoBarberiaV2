package com.barberia.ms_barberos.controller.v2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.barberia.ms_barberos.assemblers.ServicioModelAssembler;
import com.barberia.ms_barberos.dto.ServicioDTO;
import com.barberia.ms_barberos.model.Servicio;
import com.barberia.ms_barberos.service.ServicioService;

import jakarta.validation.Valid;

@RestController("servicioControllerV2")
@RequestMapping("/api/v2/servicios")
public class ServicioControllerV2 {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ServicioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ServicioDTO>>> todas() {
        List<EntityModel<ServicioDTO>> servicios = servicioService.traerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                servicios,
                linkTo(methodOn(ServicioControllerV2.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicioDTO>> porId(@PathVariable Long id) {
        Servicio servicio = servicioService.traerPorId(id);
        return ResponseEntity.ok(assembler.toModel(servicio));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicioDTO>> crear(@Valid @RequestBody ServicioDTO datos) {
        Servicio nuevo = servicioService.guardar(datos);
        return ResponseEntity
                .created(linkTo(methodOn(ServicioControllerV2.class).porId(nuevo.getIdDelServicio())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicioDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServicioDTO datos) {
        Servicio actualizado = servicioService.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
