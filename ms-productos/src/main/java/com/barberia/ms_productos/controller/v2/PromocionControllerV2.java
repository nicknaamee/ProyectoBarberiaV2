package com.barberia.ms_productos.controller.v2;

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

import com.barberia.ms_productos.assemblers.PromocionModelAssembler;
import com.barberia.ms_productos.dto.PromocionDTO;
import com.barberia.ms_productos.model.Promocion;
import com.barberia.ms_productos.service.PromocionService;

import jakarta.validation.Valid;

@RestController("promocionControllerV2")
@RequestMapping("/api/v2/promociones")
public class PromocionControllerV2 {

    @Autowired
    private PromocionService promocionService;

    @Autowired
    private PromocionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<PromocionDTO>>> todas() {
        List<EntityModel<PromocionDTO>> promociones = promocionService.traerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (promociones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                promociones,
                linkTo(methodOn(PromocionControllerV2.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PromocionDTO>> porId(@PathVariable Long id) {
        Promocion promocion = promocionService.traerPorId(id);
        return ResponseEntity.ok(assembler.toModel(promocion));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PromocionDTO>> crear(@Valid @RequestBody PromocionDTO datos) {
        Promocion nueva = promocionService.guardar(datos);
        return ResponseEntity
                .created(linkTo(methodOn(PromocionControllerV2.class).porId(nueva.getIdDeLaPromocion())).toUri())
                .body(assembler.toModel(nueva));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PromocionDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PromocionDTO datos) {
        Promocion actualizada = promocionService.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        promocionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
