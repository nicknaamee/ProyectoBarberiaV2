package com.barberia.ms_citas.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_citas.controller.v2.FacturaController;
import com.barberia.ms_citas.dto.FacturaDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FacturaModelAssembler implements RepresentationModelAssembler<FacturaDTO, EntityModel<FacturaDTO>> {

    @Override
    public EntityModel<FacturaDTO> toModel(FacturaDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(FacturaController.class).buscarPorId(dto.getIdFactura())).withSelfRel(),
                linkTo(methodOn(FacturaController.class).listarTodas()).withRel("facturas"));
    }
}
