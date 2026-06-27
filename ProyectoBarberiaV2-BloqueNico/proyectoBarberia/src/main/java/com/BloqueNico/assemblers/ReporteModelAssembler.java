package com.BloqueNico.assemblers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.BloqueNico.proyectoBarberia.dto.ReporteDTO;
import com.BloqueNico.proyectoBarberia.controller.v2.ReporteController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<ReporteDTO, EntityModel<ReporteDTO>> {

    @Override
    public EntityModel<ReporteDTO> toModel(ReporteDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ReporteController.class).obtenerPorId(dto.getIdReporte())).withSelfRel(),
                linkTo(methodOn(ReporteController.class).listarReportes()).withRel("reportes")
        );
    }
}