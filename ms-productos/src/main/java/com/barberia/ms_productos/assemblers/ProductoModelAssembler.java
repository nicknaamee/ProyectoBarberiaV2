package com.barberia.ms_productos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_productos.controller.v2.ProductoControllerV2;
import com.barberia.ms_productos.dto.ProductoDTO;
import com.barberia.ms_productos.model.Producto;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<ProductoDTO>> {

    @Override
    public EntityModel<ProductoDTO> toModel(Producto entity) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdDelProducto(entity.getIdDelProducto());
        dto.setNombreDelProducto(entity.getNombreDelProducto());
        dto.setCantidadEnStock(entity.getCantidadEnStock());
        dto.setPrecioUnitarioDelProducto(entity.getPrecioUnitarioDelProducto());

        return EntityModel.of(dto,
                linkTo(methodOn(ProductoControllerV2.class).porId(entity.getIdDelProducto())).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).todas()).withRel("productos")
        );
    }
}
