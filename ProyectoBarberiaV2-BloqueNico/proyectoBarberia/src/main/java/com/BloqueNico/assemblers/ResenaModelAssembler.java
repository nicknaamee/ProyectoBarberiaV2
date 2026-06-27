package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.proyectoBarberia.dto.ResenaDTO;
import com.BloqueNico.proyectoBarberia.controller.v2.ResenaController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ResenaModelAssembler implements RepresentationModelAssembler<ResenaDTO, EntityModel<ResenaDTO>> {

    @Override
    public EntityModel<ResenaDTO> toModel(ResenaDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ResenaController.class).obtenerPorId(dto.getIdResena())).withSelfRel(),
                linkTo(methodOn(ResenaController.class).listarTodas()).withRel("resenas")
        );
    }
}