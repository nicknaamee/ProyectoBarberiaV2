package com.BloqueNico.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.dto.ReporteDTO;
import com.BloqueNico.controller.v2.ReporteControllerV2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<ReporteDTO, EntityModel<ReporteDTO>> {

    @Override
    public EntityModel<ReporteDTO> toModel(ReporteDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ReporteControllerV2.class).obtenerPorId(dto.getIdDelReporte())).withSelfRel(),
                linkTo(methodOn(ReporteControllerV2.class).listarReportes()).withRel("reportes"));
    }
}