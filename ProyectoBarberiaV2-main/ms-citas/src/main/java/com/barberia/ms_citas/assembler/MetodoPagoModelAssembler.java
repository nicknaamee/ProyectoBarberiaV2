package com.barberia.ms_citas.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_citas.controller.v2.MetodoPagoController;
import com.barberia.ms_citas.dto.MetodoPagoDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MetodoPagoModelAssembler implements RepresentationModelAssembler<MetodoPagoDTO, EntityModel<MetodoPagoDTO>> {

    @Override
    public EntityModel<MetodoPagoDTO> toModel(MetodoPagoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(MetodoPagoController.class).traerUno(dto.getId())).withSelfRel(),
                linkTo(methodOn(MetodoPagoController.class).traerTodos()).withRel("metodos-pago"));
    }
}
