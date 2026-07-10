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

import com.barberia.ms_productos.assemblers.ProductoModelAssembler;
import com.barberia.ms_productos.dto.ProductoDTO;
import com.barberia.ms_productos.model.Producto;
import com.barberia.ms_productos.service.ProductoService;

import jakarta.validation.Valid;

@RestController("productoControllerV2")
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO>>> todas() {
        List<EntityModel<ProductoDTO>> productos = productoService.traerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                productos,
                linkTo(methodOn(ProductoControllerV2.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> porId(@PathVariable Long id) {
        Producto producto = productoService.traerPorId(id);
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> crear(@Valid @RequestBody ProductoDTO datos) {
        Producto nuevo = productoService.guardar(datos);
        return ResponseEntity
                .created(linkTo(methodOn(ProductoControllerV2.class).porId(nuevo.getIdDelProducto())).toUri())
                .body(assembler.toModel(nuevo));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO datos) {
        Producto actualizado = productoService.actualizar(id, datos);
        return ResponseEntity.ok(assembler.toModel(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
