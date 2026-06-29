package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ProductoDTO;
import com.barberia.ms_clientes.model.Producto;
import com.barberia.ms_clientes.repository.ProductoRepository;

import jakarta.transaction.Transactional;

// service de productos que se venden en la barberia
@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoDTO> traerTodos() {
        List<ProductoDTO> listaDTOs = new ArrayList<>();
        for (Producto producto : productoRepository.findAll()) {
            listaDTOs.add(convertirADTO(producto));
        }
        return listaDTOs;
    }

    public ProductoDTO traerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return convertirADTO(producto);
    }

    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombreDelProducto(dto.getNombreDelProducto());
        producto.setCantidadEnStock(dto.getCantidadEnStock());
        producto.setPrecioUnitarioDelProducto(dto.getPrecioUnitarioDelProducto());

        if (producto.getNombreDelProducto() == null || producto.getNombreDelProducto().isBlank()) {
            throw new RuntimeException("Faltan datos obligatorios del producto");
        }
        return convertirADTO(productoRepository.save(producto));
    }

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        if (dto.getNombreDelProducto() != null) {
            producto.setNombreDelProducto(dto.getNombreDelProducto());
        }
        if (dto.getCantidadEnStock() != null) {
            producto.setCantidadEnStock(dto.getCantidadEnStock());
        }
        if (dto.getPrecioUnitarioDelProducto() != null) {
            producto.setPrecioUnitarioDelProducto(dto.getPrecioUnitarioDelProducto());
        }
        return convertirADTO(productoRepository.save(producto));
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombreDelProducto(producto.getNombreDelProducto());
        dto.setCantidadEnStock(producto.getCantidadEnStock());
        dto.setPrecioUnitarioDelProducto(producto.getPrecioUnitarioDelProducto());
        return dto;
    }
}
