package com.barberia.ms_clientes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_clientes.controller.v2.ProductoControllerV2;
import com.barberia.ms_clientes.model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControllerV2.class).getProductoById(producto.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).getAllProductos()).withRel("productos"),
                linkTo(methodOn(ProductoControllerV2.class).updateProducto(producto.getIdProducto(), null)).withRel("actualizar"),
                linkTo(methodOn(ProductoControllerV2.class).deleteProducto(producto.getIdProducto())).withRel("eliminar")
        );
    }
}
