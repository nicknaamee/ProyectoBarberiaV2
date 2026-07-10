package com.barberia.ms_productos.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.barberia.ms_productos.controller.v2.PromocionControllerV2;
import com.barberia.ms_productos.dto.PromocionDTO;
import com.barberia.ms_productos.model.Promocion;

@Component
public class PromocionModelAssembler implements RepresentationModelAssembler<Promocion, EntityModel<PromocionDTO>> {

    @Override
    public EntityModel<PromocionDTO> toModel(Promocion entity) {
        PromocionDTO dto = new PromocionDTO();
        dto.setIdDeLaPromocion(entity.getIdDeLaPromocion());
        dto.setDescripcionDeLaPromocion(entity.getDescripcionDeLaPromocion());
        dto.setPorcentajeDeDescuento(entity.getPorcentajeDeDescuento());
        dto.setFechaInicioDePromocion(entity.getFechaInicioDePromocion());
        dto.setFechaFinDePromocion(entity.getFechaFinDePromocion());

        return EntityModel.of(dto,
                linkTo(methodOn(PromocionControllerV2.class).porId(entity.getIdDeLaPromocion())).withSelfRel(),
                linkTo(methodOn(PromocionControllerV2.class).todas()).withRel("promociones")
        );
    }
}
