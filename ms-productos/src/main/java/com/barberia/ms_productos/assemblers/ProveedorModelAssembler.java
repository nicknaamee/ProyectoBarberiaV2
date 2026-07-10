package com.barberia.ms_productos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_productos.controller.v2.ProveedorControllerV2;
import com.barberia.ms_productos.dto.ProveedorDTO;
import com.barberia.ms_productos.model.Proveedor;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<ProveedorDTO>> {

    @Override
    public EntityModel<ProveedorDTO> toModel(Proveedor entity) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setIdDelProveedor(entity.getIdDelProveedor());
        dto.setNombreDelProveedor(entity.getNombreDelProveedor());
        dto.setEmailDeContacto(entity.getEmailDeContacto());
        dto.setTelefonoDeContacto(entity.getTelefonoDeContacto());
        dto.setProductoQueNosProvee(entity.getProductoQueNosProvee());

        return EntityModel.of(dto,
                linkTo(methodOn(ProveedorControllerV2.class).porId(entity.getIdDelProveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class).todas()).withRel("proveedores")
        );
    }
}
