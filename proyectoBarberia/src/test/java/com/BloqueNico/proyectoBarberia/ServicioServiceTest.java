package com.BloqueNico.proyectoBarberia;

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

import com.BloqueNico.proyectoBarberia.dto.ServicioDTO;
import com.BloqueNico.proyectoBarberia.model.Servicio;
import com.BloqueNico.proyectoBarberia.repository.ServicioRepository;
import com.BloqueNico.proyectoBarberia.service.ServicioService;

@ExtendWith(MockitoExtension.class)
class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    @Test
    @DisplayName("Given servicio válido, When guardarNuevoServicio, Then retorna entidad con ID")
    void givenServicioValido_whenGuardar_thenRetornaEntidadConId() {
        // Given
        ServicioDTO dtoEntrada = new ServicioDTO();
        dtoEntrada.setNombreDelServicio("Corte y Barba");
        dtoEntrada.setPrecioDelServicio(25.0);
        dtoEntrada.setDuracionEnMinutos(45);

        Servicio servicioGuardado = new Servicio();
        servicioGuardado.setIdDelServicio(1L);
        servicioGuardado.setNombreDelServicio("Corte y Barba");
        servicioGuardado.setPrecioDelServicio(25.0);
        servicioGuardado.setDuracionEnMinutos(45);

        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioGuardado);

        // When
        Servicio resultado = servicioService.guardarNuevoServicio(dtoEntrada);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdDelServicio());
        assertEquals("Corte y Barba", resultado.getNombreDelServicio());
        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerServicioPorId, Then retorna entidad")
    void givenIdExistente_whenTraerPorId_thenRetornaEntidad() {
        // Given
        Long idBusqueda = 5L;
        Servicio servicioBD = new Servicio();
        servicioBD.setIdDelServicio(idBusqueda);
        servicioBD.setNombreDelServicio("Degradado");

        when(servicioRepository.findById(idBusqueda)).thenReturn(Optional.of(servicioBD));

        // When
        Servicio resultado = servicioService.traerServicioPorId(idBusqueda);

        // Then
        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdDelServicio());
        assertEquals("Degradado", resultado.getNombreDelServicio());
        verify(servicioRepository, times(1)).findById(idBusqueda);
    }

    @Test
    @DisplayName("Given ID inexistente, When traerServicioPorId, Then lanza RuntimeException")
    void givenIdInexistente_whenTraerPorId_thenLanzaException() {
        // Given
        Long idInexistente = 999L;
        when(servicioRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // When + Then
        assertThrows(RuntimeException.class, () -> servicioService.traerServicioPorId(idInexistente));
        verify(servicioRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Given servicios en BD, When traerTodosLosServicios, Then retorna lista")
    void givenServiciosEnBD_whenTraerTodos_thenRetornaLista() {
        // Given
        Servicio s1 = new Servicio();
        s1.setIdDelServicio(1L);
        s1.setNombreDelServicio("Corte");
        Servicio s2 = new Servicio();
        s2.setIdDelServicio(2L);
        s2.setNombreDelServicio("Barba");

        when(servicioRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        // When
        List<Servicio> resultado = servicioService.traerTodosLosServicios();

        // Then
        assertEquals(2, resultado.size());
        assertEquals("Corte", resultado.get(0).getNombreDelServicio());
        verify(servicioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given servicio existente, When eliminarServicio, Then llama a deleteById")
    void givenServicioExistente_whenEliminar_thenDeleteById() {
        // Given
        Long idEliminar = 3L;
        Servicio s = new Servicio();
        s.setIdDelServicio(idEliminar);
        when(servicioRepository.findById(idEliminar)).thenReturn(Optional.of(s));

        // When
        servicioService.eliminarServicio(idEliminar);

        // Then
        verify(servicioRepository, times(1)).deleteById(idEliminar);
    }

    @Test
    @DisplayName("Given servicio existente, When actualizarServicio, Then retorna entidad modificada")
    void givenServicioExistente_whenActualizar_thenRetornaActualizado() {
        // Given
        Long idActualizar = 1L;
        Servicio existente = new Servicio();
        existente.setIdDelServicio(idActualizar);
        existente.setNombreDelServicio("Original");

        ServicioDTO datosNuevos = new ServicioDTO();
        datosNuevos.setNombreDelServicio("Modificado");
        datosNuevos.setPrecioDelServicio(30.0);
        datosNuevos.setDuracionEnMinutos(60);

        when(servicioRepository.findById(idActualizar)).thenReturn(Optional.of(existente));
        when(servicioRepository.save(any(Servicio.class))).thenAnswer(inv -> inv.getArgument(0));

        // When
        Servicio resultado = servicioService.actualizarServicio(idActualizar, datosNuevos);

        // Then
        assertNotNull(resultado);
        assertEquals("Modificado", resultado.getNombreDelServicio());
        assertEquals(30.0, resultado.getPrecioDelServicio());
        verify(servicioRepository, times(1)).save(existente);
    }
}
