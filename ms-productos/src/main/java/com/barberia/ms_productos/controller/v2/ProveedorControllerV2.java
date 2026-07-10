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

import com.barberia.ms_productos.assemblers.ProveedorModelAssembler;
import com.barberia.ms_productos.dto.ProveedorDTO;
import com.barberia.ms_productos.model.Proveedor;
import com.barberia.ms_productos.service.ProveedorService;

import jakarta.validation.Valid;

@RestController("proveedorControllerV2")
@RequestMapping("/api/v2/proveedores")
public class ProveedorControllerV2 {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProveedorDTO>>> todas() {
        List<EntityModel<ProveedorDTO>> proveedores = proveedorService.traerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                proveedores,
                linkTo(methodOn(ProveedorControllerV2.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProveedorDTO>> porId(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.traerPorId(id);
        return ResponseEntity.ok(assembler.toModel(proveedor));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProveedorDTO>> crear(@Valid @RequestBody ProveedorDTO datos) {
        Proveedor nuevo = proveedorService.guardar(datos);
        return ResponseEntity
                .created(linkTo(methodOn(ProveedorControllerV2.class).porId(nuevo.getIdDelProveedor())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProveedorDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProveedorDTO datos) {
        Proveedor actualizado = proveedorService.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
