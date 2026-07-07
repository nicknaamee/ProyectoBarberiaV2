package com.barberia.ms_citas.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_citas.controller.v2.CitaController;
import com.barberia.ms_citas.dto.CitaDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CitaModelAssembler implements RepresentationModelAssembler<CitaDTO, EntityModel<CitaDTO>> {

    @Override
    public EntityModel<CitaDTO> toModel(CitaDTO cita) {
        return EntityModel.of(cita,
                linkTo(methodOn(CitaController.class).porId(cita.getIdCita())).withSelfRel(),
                linkTo(methodOn(CitaController.class).listarTodas()).withRel("citas"));
    }
}
