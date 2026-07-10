package com.barberia.ms_productos.service;

import com.barberia.ms_productos.dto.ProductoDTO;
import com.barberia.ms_productos.model.Producto;
import com.barberia.ms_productos.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> traerTodos() {
        log.info("buscando todos los productos");
        return repo.findAll();
    }

    public Producto traerPorId(Long id) {
        log.info("buscando producto con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el producto con id: " + id));
    }

    public Producto guardar(ProductoDTO datos) {
        log.info("guardando nuevo producto: {}", datos.getNombreDelProducto());
        Producto nuevo = new Producto();
        nuevo.setNombreDelProducto(datos.getNombreDelProducto());
        nuevo.setCantidadEnStock(datos.getCantidadEnStock());
        nuevo.setPrecioUnitarioDelProducto(datos.getPrecioUnitarioDelProducto());
        return repo.save(nuevo);
    }

    public Producto actualizar(Long id, ProductoDTO datos) {
        log.info("actualizando producto con id: {}", id);
        Producto producto = traerPorId(id);
        producto.setNombreDelProducto(datos.getNombreDelProducto());
        producto.setCantidadEnStock(datos.getCantidadEnStock());
        producto.setPrecioUnitarioDelProducto(datos.getPrecioUnitarioDelProducto());
        return repo.save(producto);
    }

    public void eliminar(Long id) {
        log.info("eliminando producto con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
