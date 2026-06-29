package com.barberia.ms_clientes.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_clientes.controller.v2.ServicioControllerV2;
import com.barberia.ms_clientes.model.Servicio;

@Component
public class ServicioModelAssembler implements RepresentationModelAssembler<Servicio, EntityModel<Servicio>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Servicio> toModel(Servicio servicio) {
        return EntityModel.of(servicio,
                linkTo(methodOn(ServicioControllerV2.class).getServicioById(servicio.getIdDelServicio())).withSelfRel(),
                linkTo(methodOn(ServicioControllerV2.class).getAllServicios()).withRel("servicios"),
                linkTo(methodOn(ServicioControllerV2.class).updateServicio(servicio.getIdDelServicio(), null)).withRel("actualizar"),
                linkTo(methodOn(ServicioControllerV2.class).deleteServicio(servicio.getIdDelServicio())).withRel("eliminar")
        );
    }
}
