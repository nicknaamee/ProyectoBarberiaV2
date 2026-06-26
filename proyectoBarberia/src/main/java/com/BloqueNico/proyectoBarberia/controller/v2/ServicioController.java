package com.BloqueNico.proyectoBarberia.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BloqueNico.proyectoBarberia.dto.ServicioDTO;
import com.BloqueNico.proyectoBarberia.model.Servicio;
import com.BloqueNico.proyectoBarberia.assemblers.ServicioModelAssembler;
import com.BloqueNico.proyectoBarberia.service.ServicioService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("servicioControllerV2")
@RequestMapping("/api/v2/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ServicioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ServicioDTO>>> listarServicios() {
        List<EntityModel<ServicioDTO>> servicios = servicioService.traerTodosLosServicios()
                .stream()
                .map(servicioService::convertirAServicioDTO) // Aduana: conversión a DTO en el controlador
                .map(assembler::toModel)                     // HATEOAS: adhesión de enlaces hipermedia
                .collect(Collectors.toList());
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(CollectionModel.of(
                servicios,
                linkTo(methodOn(ServicioController.class).listarServicios()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicioDTO>> obtenerServicio(@PathVariable Long id) {
        try {
            Servicio servicio = servicioService.traerServicioPorId(id);
            if (servicio == null) {
                return ResponseEntity.notFound().build();
            }
            ServicioDTO dto = servicioService.convertirAServicioDTO(servicio);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicioDTO>> crearServicio(@Valid @RequestBody Servicio servicio) {
        try {
            Servicio nuevoServicio = servicioService.guardarNuevoServicio(servicio);
            ServicioDTO dto = servicioService.convertirAServicioDTO(nuevoServicio);
            
            return ResponseEntity
                    .created(linkTo(methodOn(ServicioController.class).obtenerServicio(dto.getIdServicio())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicioDTO>> actualizarServicio(
            @PathVariable Long id, 
            @Valid @RequestBody Servicio servicio) {
        try {
            Servicio actualizado = servicioService.actualizarServicio(id, servicio);
            if (actualizado == null) {
                return ResponseEntity.notFound().build();
            }
            ServicioDTO dto = servicioService.convertirAServicioDTO(actualizado);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        try {
            servicioService.eliminarServicio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}