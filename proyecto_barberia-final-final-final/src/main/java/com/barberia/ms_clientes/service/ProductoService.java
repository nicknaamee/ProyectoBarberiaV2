package com.barberia.ms_clientes.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ProductoDTO;
import com.barberia.ms_clientes.model.Producto;
import com.barberia.ms_clientes.repository.ProductoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> traerTodosLosProductos() {
        log.info("buscando todos los Productos en la BD"); 
        return productoRepository.findAll();
    }

    public Producto traerProductoPorId(Long idDelProducto) {
        log.info("buscando Producto con id: {}", idDelProducto);
        return productoRepository.findById(idDelProducto)
            .orElseThrow(() -> new RuntimeException(
                "No existe ningún Producto con el id: {}" + idDelProducto)); 
    }

    public Producto guardarNuevoProducto(ProductoDTO datos) {
        log.info("guardando nuevo Producto: {}", datos.getNombreDelProducto());
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombreDelProducto(datos.getNombreDelProducto());
        nuevoProducto.setCantidadEnStock(datos.getCantidadEnStock());
        nuevoProducto.setPrecioUnitarioDelProducto(datos.getPrecioUnitarioDelProducto());
        
        return productoRepository.save(nuevoProducto);
    }

    public Producto actualizarProducto(Long idDelProducto, ProductoDTO datosNuevos) {
        log.info("actualizando Producto con id: {}", idDelProducto);
        
        Producto productoExistente = traerProductoPorId(idDelProducto);
        
        productoExistente.setNombreDelProducto(datosNuevos.getNombreDelProducto());
        productoExistente.setCantidadEnStock(datosNuevos.getCantidadEnStock());
        productoExistente.setPrecioUnitarioDelProducto(datosNuevos.getPrecioUnitarioDelProducto());
        
        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long idDelProducto) {
        log.info("eliminando Producto con id: {}", idDelProducto);
        traerProductoPorId(idDelProducto);
        productoRepository.deleteById(idDelProducto);
        log.info("Producto {} eliminado correctamente", idDelProducto);
    }

    public ProductoDTO convertirAProductoDTO(Producto producto) {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdDelProducto(producto.getIdProducto());
            productoDTO.setNombreDelProducto(producto.getNombreDelProducto());
            productoDTO.setCantidadEnStock(producto.getCantidadEnStock());
            productoDTO.setPrecioUnitarioDelProducto(producto.getPrecioUnitarioDelProducto());
            return productoDTO;
    }
}