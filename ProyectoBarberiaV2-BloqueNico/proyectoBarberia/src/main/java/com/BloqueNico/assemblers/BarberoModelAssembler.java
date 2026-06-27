package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.proyectoBarberia.dto.BarberoDTO;
import com.BloqueNico.proyectoBarberia.controller.v2.BarberoController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BarberoModelAssembler implements RepresentationModelAssembler<BarberoDTO, EntityModel<BarberoDTO>> {

    @Override
    public EntityModel<BarberoDTO> toModel(BarberoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(BarberoController.class).porId(dto.getIdBarbero())).withSelfRel(),
                linkTo(methodOn(BarberoController.class).todas()).withRel("barberos")
        );
    }
}