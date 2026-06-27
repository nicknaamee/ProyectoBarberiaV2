package com.BloqueNico.proyectoBarberia;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void testeoGuardarNuevoProducto() {
        ProductoDTO dtoEntrada = new ProductoDTO();
        dtoEntrada.setNombreDelProducto("Cera Pomada Efecto Mate");
        dtoEntrada.setPrecioUnitarioDelProducto(8500.0);
        dtoEntrada.setCantidadEnStock(15);

        Producto productoGuardado = new Producto();
        productoGuardado.setIdProducto(1L);
        productoGuardado.setNombreDelProducto("Cera Pomada Efecto Mate");
        productoGuardado.setPrecioUnitarioDelProducto(8500.0);
        productoGuardado.setCantidadEnStock(15);

        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        Producto resultado = productoService.guardarNuevoProducto(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProducto());
        assertEquals("Cera Pomada Efecto Mate", resultado.getNombreDelProducto());
        assertEquals(8500.0, resultado.getPrecioUnitarioDelProducto());
        assertEquals(15, resultado.getCantidadEnStock());

        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void testeoTraerProductoPorId() {
        Long idBusqueda = 1L;
        Producto productoBD = new Producto();
        productoBD.setIdProducto(idBusqueda);
        productoBD.setNombreDelProducto("Gel Modelador");
        productoBD.setPrecioUnitarioDelProducto(6000.0);

        when(productoRepository.findById(idBusqueda)).thenReturn(Optional.of(productoBD));

        Producto resultado = productoService.traerProductoPorId(idBusqueda);

        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdProducto());
        assertEquals("Gel Modelador", resultado.getNombreDelProducto());

        verify(productoRepository, times(1)).findById(idBusqueda);
    }

}