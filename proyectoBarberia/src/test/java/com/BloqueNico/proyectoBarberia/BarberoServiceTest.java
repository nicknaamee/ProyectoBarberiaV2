package com.BloqueNico.proyectoBarberia;

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

import com.BloqueNico.proyectoBarberia.dto.BarberoDTO;
import com.BloqueNico.proyectoBarberia.model.Barbero;
import com.BloqueNico.proyectoBarberia.repository.BarberoRepository;
import com.BloqueNico.proyectoBarberia.service.BarberoService;

@ExtendWith(MockitoExtension.class)
class BarberoServiceTest {

    @Mock
    private BarberoRepository barberoRepository;

    @InjectMocks
    private BarberoService barberoService;

    // ── GUARDAR ──

    @Test
    @DisplayName("Given barbero válido, When guardarBarbero, Then retorna barbero con ID generado")
    void givenBarberoValido_whenGuardarBarbero_thenRetornaConId() {
        // Given: un barbero sin ID (como llega del formulario)
        Barbero barberoEntrada = new Barbero();
        barberoEntrada.setNombreBarbero("Nico Mullet");
        barberoEntrada.setEspecialidadBarbero("Diseño e hilos");
        barberoEntrada.setTelefonoBarbero("+56912345678");

        // Simulamos lo que la BD retornaría tras el INSERT (con ID autoincremental)
        Barbero barberoGuardado = new Barbero();
        barberoGuardado.setIdBarbero(1L);
        barberoGuardado.setNombreBarbero("Nico Mullet");
        barberoGuardado.setEspecialidadBarbero("Diseño e hilos");
        barberoGuardado.setTelefonoBarbero("+56912345678");

        when(barberoRepository.save(any(Barbero.class))).thenReturn(barberoGuardado);

        // When: invocamos el método del servicio
        Barbero resultado = barberoService.guardarBarbero(barberoEntrada);

        // Then: verificamos que el resultado tiene el ID y los datos correctos
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBarbero());
        assertEquals("Nico Mullet", resultado.getNombreBarbero());
        verify(barberoRepository, times(1)).save(any(Barbero.class));
    }

    // ── BUSCAR POR ID (caso existente) ──

    @Test
    @DisplayName("Given ID existente en BD, When buscarPorId, Then retorna DTO con datos correctos")
    void givenIdExistente_whenBuscarPorId_thenRetornaDTO() {
        // Given: simulamos que la BD tiene un barbero con ID 5
        Long idBusqueda = 5L;
        Barbero barberoBD = new Barbero();
        barberoBD.setIdBarbero(idBusqueda);
        barberoBD.setNombreBarbero("Paola");
        barberoBD.setEspecialidadBarbero("Taper Fade");
        barberoBD.setTelefonoBarbero("+56987654321");

        when(barberoRepository.findById(idBusqueda)).thenReturn(Optional.of(barberoBD));

        // When: buscamos por ese ID
        BarberoDTO resultadoDTO = barberoService.buscarPorId(idBusqueda);

        // Then: el DTO tiene los datos del barbero encontrado
        assertNotNull(resultadoDTO);
        assertEquals("Paola", resultadoDTO.getNombreBarbero());
        assertEquals("Taper Fade", resultadoDTO.getEspecialidadBarbero());
        verify(barberoRepository, times(1)).findById(idBusqueda);
    }

    // ── BUSCAR POR ID (caso inexistente) ──

    @Test
    @DisplayName("Given ID inexistente, When buscarPorId, Then lanza RuntimeException")
    void givenIdInexistente_whenBuscarPorId_thenLanzaException() {
        // Given: el repositorio no encuentra ningún barbero con ID 999
        Long idInexistente = 999L;
        when(barberoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // When + Then: al buscar un ID que no existe, debe lanzar RuntimeException
        RuntimeException excepcion = assertThrows(RuntimeException.class,
                () -> barberoService.buscarPorId(idInexistente));

        // Then: el mensaje de error contiene el ID buscado
        assertNotNull(excepcion.getMessage());
        verify(barberoRepository, times(1)).findById(idInexistente);
    }

    // ── OBTENER TODOS ──

    @Test
    @DisplayName("Given barberos en BD, When obtenerTodos, Then retorna lista de DTOs")
    void givenBarberosEnBD_whenObtenerTodos_thenRetornaListaDTO() {
        // Given: simulamos que la BD tiene 2 barberos
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

        // When: pedimos todos los barberos
        List<BarberoDTO> resultado = barberoService.obtenerTodos();

        // Then: retorna exactamente 2 DTOs con nombres correctos
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Carlos", resultado.get(0).getNombreBarbero());
        assertEquals("Mario", resultado.get(1).getNombreBarbero());
        verify(barberoRepository, times(1)).findAll();
    }

    // ── ELIMINAR (caso exitoso) ──

    @Test
    @DisplayName("Given barbero existente, When eliminar, Then retorna mensaje de éxito y llama a delete")
    void givenBarberoExistente_whenEliminar_thenRetornaMensajeExito() {
        // Given: simulamos que el barbero con ID 3 existe en BD
        Long idEliminar = 3L;
        Barbero barberoExistente = new Barbero();
        barberoExistente.setIdBarbero(idEliminar);
        barberoExistente.setNombreBarbero("Pedro");

        when(barberoRepository.findById(idEliminar)).thenReturn(Optional.of(barberoExistente));

        // When: eliminamos el barbero
        String resultado = barberoService.eliminar(idEliminar);

        // Then: recibimos mensaje confirmando la eliminación
        assertNotNull(resultado);
        assertEquals("El barbero 'Pedro' ha sido eliminado", resultado);
        verify(barberoRepository, times(1)).delete(barberoExistente);
    }

    // ── BUSCAR POR ESPECIALIDAD ──

    @Test
    @DisplayName("Given especialidad existente, When buscarPorEspecialidad, Then retorna barberos filtrados")
    void givenEspecialidad_whenBuscarPorEspecialidad_thenRetornaFiltrados() {
        // Given: hay barberos con especialidad "Taper Fade"
        Barbero b1 = new Barbero();
        b1.setIdBarbero(1L);
        b1.setNombreBarbero("Ana");
        b1.setEspecialidadBarbero("Taper Fade");
        b1.setTelefonoBarbero("+56933333333");

        when(barberoRepository.findByEspecialidadBarbero("Taper Fade"))
                .thenReturn(Collections.singletonList(b1));

        // When: buscamos por esa especialidad
        List<BarberoDTO> resultado = barberoService.buscarPorEspecialidadBarbero("Taper Fade");

        // Then: retorna exactamente 1 barbero con esa especialidad
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombreBarbero());
        verify(barberoRepository, times(1)).findByEspecialidadBarbero("Taper Fade");
    }

    // ── BUSCAR POR ESTADO ──

    @Test
    @DisplayName("Given barberos activos, When buscarPorEstado true, Then retorna solo activos")
    void givenBarberosActivos_whenBuscarPorEstadoTrue_thenRetornaSoloActivos() {
        // Given: hay barberos con estado=true (activos)
        Barbero activo = new Barbero();
        activo.setIdBarbero(1L);
        activo.setNombreBarbero("Juan");
        activo.setEspecialidadBarbero("Corte");
        activo.setTelefonoBarbero("+56944444444");
        activo.setEstado(true);

        when(barberoRepository.findByEstado(true)).thenReturn(Collections.singletonList(activo));

        // When: filtramos por estado activo
        List<BarberoDTO> resultado = barberoService.buscarPorEstado(true);

        // Then: solo retorna barberos activos
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Juan", resultado.get(0).getNombreBarbero());
        verify(barberoRepository, times(1)).findByEstado(true);
    }

    // ── ACTUALIZAR (caso bug corregido: save(barber) en vez de save(barbero)) ──

    @Test
    @DisplayName("Given barbero existente, When actualizar, Then guarda el objeto modificado correctamente")
    void givenBarberoExistente_whenActualizar_thenGuardaObjetoModificado() {
        // Given: barbero existente en BD
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

        // When: actualizamos
        Barbero resultado = barberoService.actualizarBarberos(idActualizar, datosNuevos);

        // Then: el objeto guardado tiene los datos nuevos PERO el ID original
        assertNotNull(resultado);
        assertEquals(idActualizar, resultado.getIdBarbero());
        assertEquals("Modificado", resultado.getNombreBarbero());
        assertEquals("Barba", resultado.getEspecialidadBarbero());
        verify(barberoRepository, times(1)).save(existente);
        // Verificamos que NO se guarda el objeto datosNuevos (bug corregido)
        verify(barberoRepository, never()).save(datosNuevos);
    }
}
