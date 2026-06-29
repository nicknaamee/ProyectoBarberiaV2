package com.barberia.ms_clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberia.ms_clientes.dto.ProductosDTO;
import com.barberia.ms_clientes.service.ProductosService;

import jakarta.validation.Valid;

// controller v1 de productos por sucursal (stock por local)
@RestController("productosControllerV1")
@RequestMapping("/api/v1/productos-sucursal")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @GetMapping
    public ResponseEntity<List<ProductosDTO>> listarTodos() {
        return ResponseEntity.ok(productosService.traerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDTO> obtenerUno(@PathVariable Long id) {
        return ResponseEntity.ok(productosService.traerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductosDTO> crear(@Valid @RequestBody ProductosDTO productosDTO) {
        return new ResponseEntity<>(productosService.crear(productosDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductosDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductosDTO productosDTO) {
        return ResponseEntity.ok(productosService.actualizar(id, productosDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
