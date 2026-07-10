package com.barberia.ms_barberos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_barberos.controller.v2.ServicioControllerV2;
import com.barberia.ms_barberos.dto.ServicioDTO;
import com.barberia.ms_barberos.model.Servicio;

@Component
public class ServicioModelAssembler implements RepresentationModelAssembler<Servicio, EntityModel<ServicioDTO>> {

    @Override
    public EntityModel<ServicioDTO> toModel(Servicio entity) {
        ServicioDTO dto = new ServicioDTO();
        dto.setIdDelServicio(entity.getIdDelServicio());
        dto.setNombreDelServicio(entity.getNombreDelServicio());
        dto.setPrecioDelServicio(entity.getPrecioDelServicio());
        dto.setDuracionEnMinutos(entity.getDuracionEnMinutos());

        return EntityModel.of(dto,
                linkTo(methodOn(ServicioControllerV2.class).porId(entity.getIdDelServicio())).withSelfRel(),
                linkTo(methodOn(ServicioControllerV2.class).todas()).withRel("servicios")
        );
    }
}
