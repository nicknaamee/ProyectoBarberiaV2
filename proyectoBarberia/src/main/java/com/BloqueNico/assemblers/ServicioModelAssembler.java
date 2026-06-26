package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.proyectoBarberia.dto.ServicioDTO;
import com.BloqueNico.proyectoBarberia.controller.v2.ServicioController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ServicioModelAssembler implements RepresentationModelAssembler<ServicioDTO, EntityModel<ServicioDTO>> {

    @Override
    public EntityModel<ServicioDTO> toModel(ServicioDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ServicioController.class).obtenerServicio(dto.getIdServicio())).withSelfRel(),
                linkTo(methodOn(ServicioController.class).listarServicios()).withRel("servicios")
        );
    }
}