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

import com.BloqueNico.dto.ProductoDTO;
import com.BloqueNico.model.Producto;
import com.BloqueNico.assemblers.ProductoModelAssembler;
import com.BloqueNico.service.ProductoService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("productoControllerV2")
@RequestMapping("/api/v2/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO>>> listarProductos() {
        List<EntityModel<ProductoDTO>> productos = productoService.traerTodosLosProductos()
                .stream()
                .map(productoService::convertirAProductoDTO) // Aduana: conversión a DTO en el controlador
                .map(assembler::toModel)                     // HATEOAS: adhesión de enlaces hipermedia
                .collect(Collectors.toList());
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                productos,
                linkTo(methodOn(ProductoController.class).listarProductos()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> obtenerProducto(@PathVariable Long id) {
        try {
            Producto producto = productoService.traerProductoPorId(id);
            if (producto == null) {
                return ResponseEntity.notFound().build();
            }
            ProductoDTO dto = productoService.convertirAProductoDTO(producto);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        try {

            Producto nuevoProducto = productoService.guardarNuevoProducto(productoDTO);
            ProductoDTO dto = productoService.convertirAProductoDTO(nuevoProducto);
            
            return ResponseEntity
                    .created(linkTo(methodOn(ProductoController.class).obtenerProducto(dto.getIdDelProducto())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(id, productoDTO);
            if (productoActualizado == null) {
                return ResponseEntity.notFound().build();
            }
            ProductoDTO dto = productoService.convertirAProductoDTO(productoActualizado);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}