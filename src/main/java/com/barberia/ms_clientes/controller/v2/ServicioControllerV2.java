package com.barberia.ms_clientes.controller.v2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import com.barberia.ms_clientes.assemblers.ServicioModelAssembler;
import com.barberia.ms_clientes.model.Servicio;
import com.barberia.ms_clientes.repository.ServicioRepository;

// controller v2 de servicios con HATEOAS (HAL JSON)
@RestController
@RequestMapping("/api/v2/servicios")
public class ServicioControllerV2 {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ServicioModelAssembler assembler;

    // GET - trae todos los servicios con links
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Servicio>>> getAllServicios() {
        List<EntityModel<Servicio>> servicios = servicioRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                servicios,
                linkTo(methodOn(ServicioControllerV2.class).getAllServicios()).withSelfRel()
        ));
    }

    // GET por id - busca un servicio con links
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Servicio>> getServicioById(@PathVariable Long id) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(servicio));
    }

    // POST - crea servicio y devuelve con links
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Servicio>> createServicio(@RequestBody Servicio servicio) {
        Servicio nuevo = servicioRepository.save(servicio);
        return ResponseEntity
                .created(linkTo(methodOn(ServicioControllerV2.class).getServicioById(nuevo.getIdDelServicio())).toUri())
                .body(assembler.toModel(nuevo));
    }

    // PUT - actualiza servicio completo
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Servicio>> updateServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        servicio.setIdDelServicio(id);
        Servicio updated = servicioRepository.save(servicio);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    // DELETE - borra servicio
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        servicioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
