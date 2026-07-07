package com.BloqueNico.Testeo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.BloqueNico.dto.BarberoDTO;
import com.BloqueNico.model.Barbero;
import com.BloqueNico.repository.BarberoRepository;
import com.BloqueNico.service.BarberoService;

@ExtendWith(MockitoExtension.class)
class BarberoServiceTest {

    @Mock
    private BarberoRepository barberoRepository;

    @InjectMocks
    private BarberoService barberoService;

    @Test
    @DisplayName("Given barbero válido, When guardarBarbero, Then retorna barbero con ID generado")
    void TesteoGuardarBarbero() {
        Barbero barberoEntrada = new Barbero();
        barberoEntrada.setNombreBarbero("Nico Mullet");
        barberoEntrada.setEspecialidadBarbero("Diseño e hilos");
        barberoEntrada.setTelefonoBarbero("+56912345678");

        Barbero barberoGuardado = new Barbero();
        barberoGuardado.setIdBarbero(1L);
        barberoGuardado.setNombreBarbero("Nico Mullet");
        barberoGuardado.setEspecialidadBarbero("Diseño e hilos");
        barberoGuardado.setTelefonoBarbero("+56912345678");

        when(barberoRepository.save(any(Barbero.class))).thenReturn(barberoGuardado);

        Barbero resultado = barberoService.guardarBarbero(barberoEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBarbero());
        assertEquals("Nico Mullet", resultado.getNombreBarbero());
        verify(barberoRepository, times(1)).save(any(Barbero.class));
    }

    @Test
    @DisplayName("Given ID existente en BD, When buscarPorId, Then retorna DTO con datos correctos")
    void TesteoTraerPorId() {
        Long idBusqueda = 5L;
        Barbero barberoBD = new Barbero();
        barberoBD.setIdBarbero(idBusqueda);
        barberoBD.setNombreBarbero("Paola");
        barberoBD.setEspecialidadBarbero("Taper Fade");
        barberoBD.setTelefonoBarbero("+56987654321");

        when(barberoRepository.findById(idBusqueda)).thenReturn(Optional.of(barberoBD));

        BarberoDTO resultadoDTO = barberoService.buscarPorId(idBusqueda);

        assertNotNull(resultadoDTO);
        assertEquals("Paola", resultadoDTO.getNombreBarbero());
        assertEquals("Taper Fade", resultadoDTO.getEspecialidadBarbero());
        verify(barberoRepository, times(1)).findById(idBusqueda);
    }

    @Test
    @DisplayName("Given barberos en BD, When obtenerTodos, Then retorna lista de DTOs")
    void TesteoTraerTodos() {
        Barbero b1 = new Barbero();
        b1.setIdBarbero(1L);
        b1.setNombreBarbero("Carlos");
        b1.setEspecialidadBarbero("Corte clásico");
        b1.setTelefonoBarbero("+56911111111");

        Barbero b2 = new Barbero();
        b2.setIdBarbero(2L);
        b2.setNombreBarbero("Mario");
        b2.setEspecialidadBarbero("Barba");
        b2.setTelefonoBarbero("+56922222222");

        when(barberoRepository.findAll()).thenReturn(Arrays.asList(b1, b2));

        List<BarberoDTO> resultado = barberoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Carlos", resultado.get(0).getNombreBarbero());
        assertEquals("Mario", resultado.get(1).getNombreBarbero());
        verify(barberoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given barbero existente, When eliminar, Then retorna mensaje de éxito y llama a delete")
    void TesteoEliminarBarbero() {
        Long idEliminar = 3L;
        Barbero barberoExistente = new Barbero();
        barberoExistente.setIdBarbero(idEliminar);
        barberoExistente.setNombreBarbero("Pedro");

        when(barberoRepository.findById(idEliminar)).thenReturn(Optional.of(barberoExistente));

        String resultado = barberoService.eliminar(idEliminar);

        assertNotNull(resultado);
        assertEquals("El barbero 'Pedro' ha sido eliminado", resultado);
        verify(barberoRepository, times(1)).delete(barberoExistente);
    }

    @Test
    @DisplayName("Given especialidad existente, When buscarPorEspecialidad, Then retorna barberos filtrados")
    void TesteoTraerPorEspecialidad() {
        Barbero b1 = new Barbero();
        b1.setIdBarbero(1L);
        b1.setNombreBarbero("Ana");
        b1.setEspecialidadBarbero("Taper Fade");
        b1.setTelefonoBarbero("+56933333333");

        when(barberoRepository.findByEspecialidadBarbero("Taper Fade"))
                .thenReturn(Collections.singletonList(b1));

        List<BarberoDTO> resultado = barberoService.buscarPorEspecialidadBarbero("Taper Fade");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombreBarbero());
        verify(barberoRepository, times(1)).findByEspecialidadBarbero("Taper Fade");
    }

    @Test
    @DisplayName("Given barberos activos, When buscarPorEstado true, Then retorna solo activos")
    void TesteoTraerPorEstado() {
        Barbero activo = new Barbero();
        activo.setIdBarbero(1L);
        activo.setNombreBarbero("Juan");
        activo.setEspecialidadBarbero("Corte");
        activo.setTelefonoBarbero("+56944444444");
        activo.setEstado(true);

        when(barberoRepository.findByEstado(true)).thenReturn(Collections.singletonList(activo));

        List<BarberoDTO> resultado = barberoService.buscarPorEstado(true);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Juan", resultado.get(0).getNombreBarbero());
        verify(barberoRepository, times(1)).findByEstado(true);
    }

    @Test
    @DisplayName("Given barbero existente, When actualizar, Then guarda el objeto modificado correctamente")
    void TesteoActualizarBarbero() {
        Long idActualizar = 1L;
        Barbero existente = new Barbero();
        existente.setIdBarbero(idActualizar);
        existente.setNombreBarbero("Original");
        existente.setEspecialidadBarbero("Corte");
        existente.setTelefonoBarbero("+56900000000");

        Barbero datosNuevos = new Barbero();
        datosNuevos.setNombreBarbero("Modificado");
        datosNuevos.setEspecialidadBarbero("Barba");
        datosNuevos.setTelefonoBarbero("+56911111111");

        when(barberoRepository.findById(idActualizar)).thenReturn(Optional.of(existente));
        when(barberoRepository.save(any(Barbero.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Barbero resultado = barberoService.actualizarBarberos(idActualizar, datosNuevos);

        assertNotNull(resultado);
        assertEquals(idActualizar, resultado.getIdBarbero());
        assertEquals("Modificado", resultado.getNombreBarbero());
        assertEquals("Barba", resultado.getEspecialidadBarbero());
        verify(barberoRepository, times(1)).save(existente);
        verify(barberoRepository, never()).save(datosNuevos);
    }
}
