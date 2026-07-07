package com.barberia.ms_clientes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_clientes.controller.v2.ResenaControllerV2;
import com.barberia.ms_clientes.model.Resena;

@Component
public class ResenaModelAssembler implements RepresentationModelAssembler<Resena, EntityModel<Resena>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Resena> toModel(Resena resena) {
        return EntityModel.of(resena,
                linkTo(methodOn(ResenaControllerV2.class).getResenaById(resena.getIdDeLaResena())).withSelfRel(),
                linkTo(methodOn(ResenaControllerV2.class).getAllResenas()).withRel("resenas"),
                linkTo(methodOn(ResenaControllerV2.class).getResenasByBarbero(resena.getBarberoId())).withRel("resenas-por-barbero"),
                linkTo(methodOn(ResenaControllerV2.class).deleteResena(resena.getIdDeLaResena())).withRel("eliminar")
        );
    }
}
