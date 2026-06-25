package com.BloqueNico.proyectoBarberia.controller;

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

import com.BloqueNico.proyectoBarberia.dto.ProductoDTO;
import com.BloqueNico.proyectoBarberia.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoService.traerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Long id) {
        ProductoDTO productoDTO = productoService.traerProductoPorId(id);
        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProductoDTO = productoService.guardarNuevoProducto(productoDTO);
        return new ResponseEntity<>(nuevoProductoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizadoDTO = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}