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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.ms_clientes.assemblers.ResenaModelAssembler;
import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.repository.ResenaRepository;

// controller v2 de reseñas con HATEOAS (HAL JSON)
@RestController
@RequestMapping("/api/v2/resenas")
public class ResenaControllerV2 {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ResenaModelAssembler assembler;

    // GET - trae todas las reseñas con links
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Resena>>> getAllResenas() {
        List<EntityModel<Resena>> resenas = resenaRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                resenas,
                linkTo(methodOn(ResenaControllerV2.class).getAllResenas()).withSelfRel()
        ));
    }

    // GET por id - busca una reseña con links
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Resena>> getResenaById(@PathVariable Long id) {
        Resena resena = resenaRepository.findById(id).orElse(null);
        if (resena == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(resena));
    }

    // GET por barbero - filtra reseñas de un barbero
    @GetMapping(value = "/barbero/{idBarbero}", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Resena>> getResenasByBarbero(@PathVariable Long idBarbero) {
        List<EntityModel<Resena>> resenas = resenaRepository.findByBarberoId(idBarbero).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                resenas,
                linkTo(methodOn(ResenaControllerV2.class).getResenasByBarbero(idBarbero)).withSelfRel()
        );
    }

    // POST - crea reseña y devuelve con links
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Resena>> createResena(@RequestBody Resena resena) {
        Resena nueva = resenaRepository.save(resena);
        return ResponseEntity
                .created(linkTo(methodOn(ResenaControllerV2.class).getResenaById(nueva.getIdDeLaResena())).toUri())
                .body(assembler.toModel(nueva));
    }

    // DELETE - borra reseña
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        Resena resena = resenaRepository.findById(id).orElse(null);
        if (resena == null) {
            return ResponseEntity.notFound().build();
        }
        resenaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
