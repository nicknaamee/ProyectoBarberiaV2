package com.BloqueNico.proyectoBarberia.service;

import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.model.Producto;

@Service
public class ProductoValidaciones {

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
}