package com.BloqueNico.proyectoBarberia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.ProductoDTO;
import com.BloqueNico.proyectoBarberia.model.Producto;
import com.BloqueNico.proyectoBarberia.repository.ProductoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductoService {

    @Autowired 
    private ProductoRepository productoRepository;

    @Autowired 
    private ProductoValidaciones validaciones;

    public List<ProductoDTO> traerTodosLosProductos() {
        log.info("Buscando todos los Productos en la BD"); 
        return productoRepository.findAll().stream()
                .map(validaciones::convertirADTO) 
                .toList();
    }

    public ProductoDTO traerProductoPorId(Long idDelProducto) {
        log.info("Buscando Producto con id: {}", idDelProducto);
        Producto producto = buscarEntidadPorId(idDelProducto);
        return validaciones.convertirADTO(producto);
    }

    public ProductoDTO guardarNuevoProducto(ProductoDTO datos) {
        log.info("Guardando nuevo Producto: {}", datos.getNombreDelProducto());
        
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombreDelProducto(datos.getNombreDelProducto());
        nuevoProducto.setCantidadEnStock(datos.getCantidadEnStock());
        nuevoProducto.setPrecioUnitarioDelProducto(datos.getPrecioUnitarioDelProducto());
        if (!validaciones.validarProducto(nuevoProducto)) {
            throw new RuntimeException("Los datos del producto son inválidos (verifique el stock o precio)");
        }
        return validaciones.convertirADTO(productoRepository.save(nuevoProducto));
    }

    public ProductoDTO actualizarProducto(Long idDelProducto, ProductoDTO datosNuevos) {
        log.info("Actualizando Producto con id: {}", idDelProducto);
        Producto productoExistente = buscarEntidadPorId(idDelProducto);
        productoExistente.setNombreDelProducto(datosNuevos.getNombreDelProducto());
        productoExistente.setCantidadEnStock(datosNuevos.getCantidadEnStock());
        productoExistente.setPrecioUnitarioDelProducto(datosNuevos.getPrecioUnitarioDelProducto());
        if (!validaciones.validarProducto(productoExistente)) {
            throw new RuntimeException("La actualización del producto contiene valores numéricos inválidos");
        }
        
        return validaciones.convertirADTO(productoRepository.save(productoExistente));
    }

    public void eliminarProducto(Long idDelProducto) {
        log.info("Eliminando Producto con id: {}", idDelProducto);
        if (!productoRepository.existsById(idDelProducto)) {
            throw new RuntimeException("No existe ningún Producto con el id: " + idDelProducto);
        }
        productoRepository.deleteById(idDelProducto);
        log.info("Producto {} eliminado correctamente", idDelProducto);
    }

    //Método auxiliar (sugerido por la IA)
    private Producto buscarEntidadPorId(Long idDelProducto) {
        return productoRepository.findById(idDelProducto)
            .orElseThrow(() -> new RuntimeException("No existe ningún Producto con el id: " + idDelProducto)); // ¡CORREGIDO! Sin el {}
    }
}