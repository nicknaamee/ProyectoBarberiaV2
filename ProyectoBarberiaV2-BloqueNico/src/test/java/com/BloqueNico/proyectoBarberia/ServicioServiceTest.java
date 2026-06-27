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

    @Test
    void testeoObtenerTdos() {
        Servicio servicio = new Servicio();
        servicio.setIdDelServicio(1L);
        servicio.setNombreDelServicio("Corte Completo");

        when(servicioRepository.findAll()).thenReturn(List.of(servicio));

        List<Servicio> resultado = servicioService.traerTodosLosServicios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Corte Completo", resultado.get(0).getNombreDelServicio());
        verify(servicioRepository, times(1)).findAll();
    }
    @Test
    void testeoActualizar() {
        Long id = 1L;
        Servicio existente = new Servicio();
        existente.setIdDelServicio(id);
        existente.setNombreDelServicio("Corte Simple");
        existente.setPrecioDelServicio(8000.0);
        existente.setDuracionEnMinutos(20);

        ServicioDTO nuevosDatos = new ServicioDTO();
        nuevosDatos.setNombreDelServicio("Corte Premium");
        nuevosDatos.setPrecioDelServicio(12000.0);
        nuevosDatos.setDuracionEnMinutos(40);

        when(servicioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(servicioRepository.save(any(Servicio.class))).thenReturn(existente);

        Servicio resultado = servicioService.actualizarServicio(id, nuevosDatos);

        assertNotNull(resultado);
        assertEquals("Corte Premium", resultado.getNombreDelServicio());
        assertEquals(12000.0, resultado.getPrecioDelServicio());
        assertEquals(40, resultado.getDuracionEnMinutos());
        verify(servicioRepository, times(1)).findById(id);
        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }
    
    @Test
    void testeoEliminar() {
        Long id = 1L;
        Servicio servicio = new Servicio();
        servicio.setIdDelServicio(id);

        when(servicioRepository.findById(id)).thenReturn(Optional.of(servicio));
        doNothing().when(servicioRepository).deleteById(id);

        servicioService.eliminarServicio(id);

        verify(servicioRepository, times(1)).findById(id);
        verify(servicioRepository, times(1)).deleteById(id);
    }

    @Test
    void testeoConvertirDTO() {
        Servicio servicio = new Servicio();
        servicio.setIdDelServicio(1L);
        servicio.setNombreDelServicio("Perfilado de Barba");
        servicio.setPrecioDelServicio(6000.0);
        servicio.setDuracionEnMinutos(15);

        ServicioDTO resultado = servicioService.convertirAServicioDTO(servicio);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdDelServicio());
        assertEquals("Perfilado de Barba", resultado.getNombreDelServicio());
        assertEquals(6000.0, resultado.getPrecioDelServicio());
        assertEquals(15, resultado.getDuracionEnMinutos());
    }

}