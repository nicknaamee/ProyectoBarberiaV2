package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.dto.ClienteDTO;
import com.BloqueNico.controller.v2.ClienteControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteDTO, EntityModel<ClienteDTO>> {

    @Override
    public EntityModel<ClienteDTO> toModel(ClienteDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ClienteControllerV2.class).traerUno(dto.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).traerTodos()).withRel("clientes"));
    }
}