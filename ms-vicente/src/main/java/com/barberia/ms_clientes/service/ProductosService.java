package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ProductosDTO;
import com.barberia.ms_clientes.model.Productos;
import com.barberia.ms_clientes.repository.ProductosRepository;

import jakarta.transaction.Transactional;

// service de productos por sucursal, maneja el stock por local
@Service
@Transactional
public class ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    public List<ProductosDTO> traerTodos() {
        List<ProductosDTO> listaDTOs = new ArrayList<>();
        for (Productos producto : productosRepository.findAll()) {
            listaDTOs.add(convertirADTO(producto));
        }
        return listaDTOs;
    }

    public ProductosDTO traerPorId(Long id) {
        Productos producto = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto de sucursal no encontrado con id: " + id));
        return convertirADTO(producto);
    }

    public ProductosDTO crear(ProductosDTO dto) {
        Productos producto = new Productos();
        producto.setIdSucursal(dto.getIdSucursal());
        producto.setProductoId(dto.getProductoId());
        producto.setStock(dto.getStock());

        if (producto.getIdSucursal() == null || producto.getProductoId() == null) {
            throw new RuntimeException("Faltan datos obligatorios del producto por sucursal");
        }
        return convertirADTO(productosRepository.save(producto));
    }

    public ProductosDTO actualizar(Long id, ProductosDTO dto) {
        Productos producto = productosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto de sucursal no encontrado con id: " + id));

        if (dto.getIdSucursal() != null) {
            producto.setIdSucursal(dto.getIdSucursal());
        }
        if (dto.getProductoId() != null) {
            producto.setProductoId(dto.getProductoId());
        }
        if (dto.getStock() != null) {
            producto.setStock(dto.getStock());
        }
        return convertirADTO(productosRepository.save(producto));
    }

    public void eliminar(Long id) {
        productosRepository.deleteById(id);
    }

    private ProductosDTO convertirADTO(Productos producto) {
        ProductosDTO dto = new ProductosDTO();
        dto.setId(producto.getId());
        dto.setIdSucursal(producto.getIdSucursal());
        dto.setProductoId(producto.getProductoId());
        dto.setStock(producto.getStock());
        return dto;
    }
}
