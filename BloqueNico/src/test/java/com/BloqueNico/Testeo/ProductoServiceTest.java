package com.BloqueNico.Testeo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.BloqueNico.dto.ProductoDTO;
import com.BloqueNico.model.Producto;
import com.BloqueNico.repository.ProductoRepository;
import com.BloqueNico.service.ProductoService;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    @DisplayName("Given producto válido, When guardarNuevoProducto, Then retorna producto con ID")
    void TesteoGuardarProducto() {
        ProductoDTO dtoEntrada = new ProductoDTO();
        dtoEntrada.setNombreDelProducto("Cera mate");
        dtoEntrada.setCantidadEnStock(10);
        dtoEntrada.setPrecioUnitarioDelProducto(15.5);

        Producto productoGuardado = new Producto();
        productoGuardado.setIdProducto(1L);
        productoGuardado.setNombreDelProducto("Cera mate");
        productoGuardado.setCantidadEnStock(10);
        productoGuardado.setPrecioUnitarioDelProducto(15.5);

        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = productoService.guardarNuevoProducto(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProducto());
        assertEquals("Cera mate", resultado.getNombreDelProducto());
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerProductoPorId, Then retorna entidad correcta")
    void TesteoTraerProducto() {
        Long idBusqueda = 5L;
        Producto productoBD = new Producto();
        productoBD.setIdProducto(idBusqueda);
        productoBD.setNombreDelProducto("Gel fijador");

        when(productoRepository.findById(idBusqueda)).thenReturn(Optional.of(productoBD));

        Producto resultado = productoService.traerProductoPorId(idBusqueda);

        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdProducto());
        assertEquals("Gel fijador", resultado.getNombreDelProducto());
        verify(productoRepository, times(1)).findById(idBusqueda);
    }

    @Test
    @DisplayName("Given productos en BD, When traerTodosLosProductos, Then retorna lista")
    void TesteoTraerTodos() {
        Producto p1 = new Producto();
        p1.setIdProducto(1L);
        p1.setNombreDelProducto("Cera");
        Producto p2 = new Producto();
        p2.setIdProducto(2L);
        p2.setNombreDelProducto("Gel");

        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> resultado = productoService.traerTodosLosProductos();

        assertEquals(2, resultado.size());
        assertEquals("Cera", resultado.get(0).getNombreDelProducto());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given producto existente, When eliminarProducto, Then se ejecuta deleteById sin error")
    void TesteoEliminarProducto() {
        Long idEliminar = 3L;
        Producto p = new Producto();
        p.setIdProducto(idEliminar);
        when(productoRepository.findById(idEliminar)).thenReturn(Optional.of(p));

        productoService.eliminarProducto(idEliminar);

        verify(productoRepository, times(1)).deleteById(idEliminar);
    }

    @Test
    @DisplayName("Given producto existente, When actualizarProducto, Then retorna entidad con datos modificados")
    void TesteoActualizarProducto() {
        Long idActualizar = 1L;
        Producto existente = new Producto();
        existente.setIdProducto(idActualizar);
        existente.setNombreDelProducto("Original");

        ProductoDTO datosNuevos = new ProductoDTO();
        datosNuevos.setNombreDelProducto("Modificado");
        datosNuevos.setCantidadEnStock(50);
        datosNuevos.setPrecioUnitarioDelProducto(20.0);

        when(productoRepository.findById(idActualizar)).thenReturn(Optional.of(existente));
        when(productoRepository.save(any(Producto.class))).thenAnswer(inv -> inv.getArgument(0));

        Producto resultado = productoService.actualizarProducto(idActualizar, datosNuevos);

        assertNotNull(resultado);
        assertEquals("Modificado", resultado.getNombreDelProducto());
        assertEquals(50, resultado.getCantidadEnStock());
        verify(productoRepository, times(1)).save(existente);
    }
}
