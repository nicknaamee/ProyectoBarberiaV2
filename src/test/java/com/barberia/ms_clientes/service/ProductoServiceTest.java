package com.barberia.ms_clientes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.barberia.ms_clientes.model.Producto;
import com.barberia.ms_clientes.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    private Producto createProducto() {
        return new Producto(1L, "Shampoo", 50, 12.99);
    }

    @Test
    public void testFindAll() {
        when(productoRepository.findAll()).thenReturn(List.of(createProducto()));
        var productos = productoService.traerTodos();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testFindById() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(createProducto()));
        var producto = productoService.traerPorId(1L);
        assertNotNull(producto);
        assertEquals("Shampoo", producto.getNombreDelProducto());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(productoRepository).deleteById(1L);
        productoService.eliminar(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}
