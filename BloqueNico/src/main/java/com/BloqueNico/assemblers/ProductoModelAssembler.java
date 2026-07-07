package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.dto.ProductoDTO;
import com.BloqueNico.controller.v2.ProductoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>> {

    @Override
    public EntityModel<ProductoDTO> toModel(ProductoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ProductoController.class).obtenerProducto(dto.getIdDelProducto())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).listarProductos()).withRel("productos")
        );
    }
}