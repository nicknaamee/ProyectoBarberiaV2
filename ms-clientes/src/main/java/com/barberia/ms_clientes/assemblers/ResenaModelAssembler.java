package com.barberia.ms_clientes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_clientes.controller.v2.ResenaControllerV2;
import com.barberia.ms_clientes.dto.ResenaDTO;
import com.barberia.ms_clientes.model.Resena;

@Component
public class ResenaModelAssembler implements RepresentationModelAssembler<Resena, EntityModel<ResenaDTO>> {

    @Override
    public EntityModel<ResenaDTO> toModel(Resena entity) {
        ResenaDTO dto = new ResenaDTO();
        dto.setIdDeLaResena(entity.getIdDeLaResena());
        dto.setIdDelClienteQueOpina(entity.getIdDelClienteQueOpina());
        dto.setIdDelBarberoEvaluado(entity.getIdDelBarberoEvaluado());
        dto.setCalificacionDeEstrellas(entity.getCalificacionDeEstrellas());
        dto.setComentarioDelCliente(entity.getComentarioDelCliente());
        dto.setFechaDeLaResena(entity.getFechaDeLaResena());

        return EntityModel.of(dto,
                linkTo(methodOn(ResenaControllerV2.class).porId(entity.getIdDeLaResena())).withSelfRel(),
                linkTo(methodOn(ResenaControllerV2.class).todas()).withRel("resenas")
        );
    }
}
