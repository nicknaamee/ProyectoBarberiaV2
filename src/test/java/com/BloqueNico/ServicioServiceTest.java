package com.BloqueNico;

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

import com.BloqueNico.dto.ServicioDTO;
import com.BloqueNico.model.Servicio;
import com.BloqueNico.repository.ServicioRepository;
import com.BloqueNico.service.ServicioService;

@ExtendWith(MockitoExtension.class)
class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    @Test
    void testeoGuardarServicio() {
        ServicioDTO dtoEntrada = new ServicioDTO();
        dtoEntrada.setNombreDelServicio("Corte Degradado");
        dtoEntrada.setPrecioDelServicio(12000.0);
        dtoEntrada.setDuracionEnMinutos(30);

        Servicio servicioGuardado = new Servicio();
        servicioGuardado.setIdDelServicio(1L);
        servicioGuardado.setNombreDelServicio("Corte Degradado");
        servicioGuardado.setPrecioDelServicio(12000.0);
        servicioGuardado.setDuracionEnMinutos(30);

        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioGuardado);

        Servicio resultado = servicioService.guardarNuevoServicio(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdDelServicio());
        assertEquals("Corte Degradado", resultado.getNombreDelServicio());
        assertEquals(12000.0, resultado.getPrecioDelServicio());
        assertEquals(30, resultado.getDuracionEnMinutos());

        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }

    @Test
    void testeoTraerServicioPorIdExistente() {
        Long idBusqueda = 1L;
        Servicio servicioBD = new Servicio();
        servicioBD.setIdDelServicio(idBusqueda);
        servicioBD.setNombreDelServicio("Barba Completa");
        servicioBD.setPrecioDelServicio(8000.0);

        when(servicioRepository.findById(idBusqueda)).thenReturn(Optional.of(servicioBD));

        Servicio resultado = servicioService.traerServicioPorId(idBusqueda);

        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdDelServicio());
        assertEquals("Barba Completa", resultado.getNombreDelServicio());

        verify(servicioRepository, times(1)).findById(idBusqueda);
    }

}