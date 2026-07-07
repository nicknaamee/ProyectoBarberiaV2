package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.BloqueNico.controller.v2.BarberoControllerV2;
import com.BloqueNico.dto.BarberoDTO;

@Component
public class BarberoModelAssembler implements RepresentationModelAssembler<BarberoDTO, EntityModel<BarberoDTO>> {

    @Override
    public EntityModel<BarberoDTO> toModel(BarberoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(BarberoControllerV2.class).porId(dto.getIdBarbero())).withSelfRel(),
                linkTo(methodOn(BarberoControllerV2.class).todas()).withRel("barberos")
        );
    }
}