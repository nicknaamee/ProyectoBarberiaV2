package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ProductosDTO;
import com.barberia.ms_clientes.model.Producto;
import com.barberia.ms_clientes.model.Productos;
import com.barberia.ms_clientes.repository.ProductoRepository;
import com.barberia.ms_clientes.repository.ProductosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class ProductosService {

    @Autowired
    private ProductosRepository repo;

    @Autowired
    private ProductoRepository productoRepo; 

    public List<Productos> traerTodos() {
        log.info("buscando todos los productos por sucursal");
        return repo.findAll();
    }

    public Productos traerPorId(Long id) {
        log.info("buscando producto por sucursal con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el registro con id: " + id));
    }

    public Productos guardar(ProductosDTO datos) {
        log.info("guardando nuevo producto por sucursal");
        
        Producto productoExistente = productoRepo.findById(datos.getIdProducto())
            .orElseThrow(() -> new RuntimeException("El producto base no existe"));

        Productos nuevo = new Productos();
        nuevo.setIdSucursal(datos.getIdSucursal());
        
        nuevo.setProducto(productoExistente); 
        nuevo.setStock(datos.getStock());
        
        return repo.save(nuevo);
    }

    public Productos actualizar(Long id, ProductosDTO datos) {
        log.info("actualizando producto por sucursal con id: {}", id);
        
        Productos registroExistente = traerPorId(id);
        
        Producto productoExistente = productoRepo.findById(datos.getIdProducto())
            .orElseThrow(() -> new RuntimeException("El producto base no existe"));

        registroExistente.setIdSucursal(datos.getIdSucursal());
        registroExistente.setProducto(productoExistente); 
        registroExistente.setStock(datos.getStock());
        
        return repo.save(registroExistente);
    }

    public void eliminar(Long id) {
        log.info("eliminando producto por sucursal con id: {}", id);
        repo.deleteById(id);
    }
}
