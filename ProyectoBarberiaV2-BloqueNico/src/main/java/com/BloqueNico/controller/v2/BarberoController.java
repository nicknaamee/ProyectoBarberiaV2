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

import com.BloqueNico.dto.BarberoDTO;
import com.BloqueNico.assemblers.BarberoModelAssembler;
import com.BloqueNico.model.Barbero;
import com.BloqueNico.service.BarberoService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("barberoControllerV2") 
@RequestMapping("/api/v2/barberos")
public class BarberoController {

    @Autowired
    private BarberoService barberoService;

    @Autowired
    private BarberoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<BarberoDTO>>> todas() {
        List<EntityModel<BarberoDTO>> barberos = barberoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (barberos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                barberos,
                linkTo(methodOn(BarberoController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<BarberoDTO>> porId(@PathVariable Long id) {
        try {
            BarberoDTO dto = barberoService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<BarberoDTO>> registrar(@Valid @RequestBody Barbero barbero) {
    try {
        Barbero nuevoBarbero = barberoService.guardarBarbero(barbero);
        BarberoDTO dto = barberoService.convertirADTO(nuevoBarbero);
        return ResponseEntity
                .created(linkTo(methodOn(BarberoController.class).porId(dto.getIdBarbero())).toUri())
                .body(assembler.toModel(dto));
                
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }
}

@PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
public ResponseEntity<EntityModel<BarberoDTO>> actualizar(@PathVariable Long id, @Valid @RequestBody Barbero barbero) {
    try {
        Barbero barberoActualizado = barberoService.actualizarBarberos(id, barbero); 
        if (barberoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        BarberoDTO dto = barberoService.convertirADTO(barberoActualizado);
        return ResponseEntity.ok(assembler.toModel(dto));
        
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            barberoService.eliminar(id); 
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/especialidad/{especialidad}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<BarberoDTO>>> porEspecialidad(@PathVariable String especialidad) {
        List<EntityModel<BarberoDTO>> barberos = barberoService.buscarPorEspecialidadBarbero(especialidad).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (barberos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                barberos,
                linkTo(methodOn(BarberoController.class).porEspecialidad(especialidad)).withSelfRel()
        ));
    }

    @GetMapping(value = "/activos", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<BarberoDTO>>> obtenerActivos() {
        List<EntityModel<BarberoDTO>> barberos = barberoService.buscarPorEstado(true).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (barberos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                barberos,
                linkTo(methodOn(BarberoController.class).obtenerActivos()).withSelfRel()
        ));
    }
}