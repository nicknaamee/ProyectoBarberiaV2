package com.barberia.ms_citas.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_citas.controller.v2.MetodosPagoController;
import com.barberia.ms_citas.dto.MetodosPagoDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MetodosPagoModelAssembler implements RepresentationModelAssembler<MetodosPagoDTO, EntityModel<MetodosPagoDTO>> {

    @Override
    public EntityModel<MetodosPagoDTO> toModel(MetodosPagoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(MetodosPagoController.class).traerUno(dto.getId())).withSelfRel(),
                linkTo(methodOn(MetodosPagoController.class).traerTodos()).withRel("metodos-pago-sucursal"));
    }
}
