package com.BloqueNico.proyectoBarberia.controller.v2;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BloqueNico.proyectoBarberia.dto.ResenaDTO;
import com.BloqueNico.proyectoBarberia.model.Resena;
import com.BloqueNico.proyectoBarberia.assemblers.ResenaModelAssembler;
import com.BloqueNico.proyectoBarberia.service.ResenaService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("resenaControllerV2")
@RequestMapping("/api/v2/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private ResenaModelAssembler assembler;


    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ResenaDTO>>> listarTodas() {
        List<EntityModel<ResenaDTO>> resenas = resenaService.traerTodasLasResenas()
                .stream()
                .map(resenaService::convertirAResenaDTO) // Conversión a DTO en el controlador
                .map(assembler::toModel)                 // Adición de links hipermedia
                .collect(Collectors.toList());
        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                resenas,
                linkTo(methodOn(ResenaController.class).listarTodas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ResenaDTO>> obtenerPorId(@PathVariable Long id) {
        try {
            Resena resena = resenaService.traerResenaPorId(id);
            if (resena == null) {
                return ResponseEntity.notFound().build();
            }
            ResenaDTO dto = resenaService.convertirAResenaDTO(resena);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ResenaDTO>> crearResena(@Valid @RequestBody Resena resena) {
        try {
            Resena nuevaResena = resenaService.guardarNuevaResena(resena);
            ResenaDTO dto = resenaService.convertirAResenaDTO(nuevaResena);
            
            return ResponseEntity
                    .created(linkTo(methodOn(ResenaController.class).obtenerPorId(dto.getIdResena())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        try {
            resenaService.eliminarResena(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}