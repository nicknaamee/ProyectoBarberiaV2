package com.barberia.ms_productos.service;

import com.barberia.ms_productos.dto.ProductosDTO;
import com.barberia.ms_productos.model.Productos;
import com.barberia.ms_productos.repository.ProductosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductosService {

    @Autowired
    private ProductosRepository repo;

    // trae todos los productos por sucursal
    public List<Productos> traerTodos() {
        log.info("buscando todos los productos por sucursal");
        return repo.findAll();
    }

    // busca un producto por su id
    public Productos traerPorId(Long id) {
        log.info("buscando producto por sucursal con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el producto con id: " + id));
    }

    // guarda un nuevo producto
    public Productos guardar(ProductosDTO datos) {
        log.info("guardando nuevo producto por sucursal");
        Productos nuevo = new Productos();
        nuevo.setIdSucursal(datos.getIdSucursal());
        nuevo.setIdProducto(datos.getIdProducto());
        nuevo.setStock(datos.getStock());
        return repo.save(nuevo);
    }

    // actualiza un producto existente
    public Productos actualizar(Long id, ProductosDTO datos) {
        log.info("actualizando producto por sucursal con id: {}", id);
        Productos producto = traerPorId(id);
        producto.setIdSucursal(datos.getIdSucursal());
        producto.setIdProducto(datos.getIdProducto());
        producto.setStock(datos.getStock());
        return repo.save(producto);
    }

    // elimina un producto
    public void eliminar(Long id) {
        log.info("eliminando producto por sucursal con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
