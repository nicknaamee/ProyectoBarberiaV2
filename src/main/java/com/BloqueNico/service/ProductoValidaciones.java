package com.BloqueNico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.BloqueNico.dto.ProductoDTO;
import com.BloqueNico.model.Producto;

@Service
public class ProductoValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder; // Mantenemos la estructura requerida por el docente

    public Boolean validarProducto(Producto producto) {
        if (producto.getNombreDelProducto() == null || producto.getNombreDelProducto().trim().isEmpty()) {
            return false;
        }
        if (producto.getCantidadEnStock() == null || producto.getPrecioUnitarioDelProducto() == null) {
            return false;
        }
        if (producto.getNombreDelProducto().length() > 100) {
            return false;
        }
        if (producto.getCantidadEnStock() < 0) {
            return false;
        }
        if (producto.getPrecioUnitarioDelProducto() <= 0) {
            return false;
        }
        return true;
    }

    public ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdDelProducto(producto.getIdProducto());
        dto.setNombreDelProducto(producto.getNombreDelProducto());
        dto.setCantidadEnStock(producto.getCantidadEnStock());
        dto.setPrecioUnitarioDelProducto(producto.getPrecioUnitarioDelProducto());
        return dto;
    }
}