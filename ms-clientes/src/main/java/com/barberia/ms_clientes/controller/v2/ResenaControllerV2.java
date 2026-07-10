package com.barberia.ms_clientes.controller.v2;

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

import com.barberia.ms_clientes.assemblers.ResenaModelAssembler;
import com.barberia.ms_clientes.dto.ResenaDTO;
import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.service.ResenaService;

import jakarta.validation.Valid;

@RestController("resenaControllerV2")
@RequestMapping("/api/v2/resenas")
public class ResenaControllerV2 {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private ResenaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ResenaDTO>>> todas() {
        List<EntityModel<ResenaDTO>> resenas = resenaService.traerTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                resenas,
                linkTo(methodOn(ResenaControllerV2.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ResenaDTO>> porId(@PathVariable Long id) {
        Resena resena = resenaService.traerPorId(id);
        return ResponseEntity.ok(assembler.toModel(resena));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ResenaDTO>> crear(@Valid @RequestBody ResenaDTO datos) {
        Resena nueva = resenaService.guardar(datos);
        return ResponseEntity
                .created(linkTo(methodOn(ResenaControllerV2.class).porId(nueva.getIdDeLaResena())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ResenaDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ResenaDTO datos) {
        Resena actualizada = resenaService.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
