package com.barberia.ms_citas.controller.v2;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barberia.ms_citas.assembler.FacturaModelAssembler;
import com.barberia.ms_citas.dto.FacturaDTO;
import com.barberia.ms_citas.service.FacturaService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("facturaControllerV2")
@RequestMapping("/api/v2/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<FacturaDTO>>> listarTodas() {
        List<EntityModel<FacturaDTO>> facturas = facturaService.obtenerTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (facturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                facturas,
                linkTo(methodOn(FacturaController.class).listarTodas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<FacturaDTO>> buscarPorId(@PathVariable Long id) {
        try {
            FacturaDTO dto = facturaService.obtenerPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/emitir", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<FacturaDTO>> emitir(@RequestParam Long idCita, @RequestParam String metodo) {
        try {
            FacturaDTO dto = facturaService.generarFactura(idCita, metodo);
            return ResponseEntity
                    .created(linkTo(methodOn(FacturaController.class).buscarPorId(dto.getIdFactura())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(value = "/{id}/metodo-pago", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<FacturaDTO>> cambiarMetodoPago(@PathVariable Long id, @RequestParam String nuevoMetodo) {
        try {
            FacturaDTO dto = facturaService.cambiarMetodoPago(id, nuevoMetodo);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            facturaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
