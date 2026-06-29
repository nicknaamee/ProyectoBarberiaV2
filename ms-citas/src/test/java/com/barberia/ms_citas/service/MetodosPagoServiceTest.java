package com.barberia.ms_citas.service;

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

import com.barberia.ms_citas.dto.MetodosPagoDTO;
import com.barberia.ms_citas.model.MetodosPago;
import com.barberia.ms_citas.repository.MetodosPagoRepository;

@ExtendWith(MockitoExtension.class)
class MetodosPagoServiceTest {

    @Mock
    private MetodosPagoRepository repo;

    @Mock
    private MetodosPagoValidaciones validaciones;

    @InjectMocks
    private MetodosPagoService service;

    @Test
    @DisplayName("Given datos válidos, When guardar, Then guarda y retorna DTO")
    void givenDatosValidos_whenGuardar_thenRetornaDTO() {
        // Given
        MetodosPagoDTO datosEntrada = new MetodosPagoDTO();
        datosEntrada.setIdSucursal(10L);
        datosEntrada.setIdMetodoPago(5L);

        MetodosPago metodoGuardado = new MetodosPago();
        metodoGuardado.setId(1L);
        metodoGuardado.setIdSucursal(10L);
        metodoGuardado.setIdMetodoPago(5L);

        MetodosPagoDTO dtoGuardado = new MetodosPagoDTO();
        dtoGuardado.setId(1L);
        dtoGuardado.setIdSucursal(10L);
        dtoGuardado.setIdMetodoPago(5L);

        when(validaciones.validarNullVacio(datosEntrada)).thenReturn(true);
        when(repo.save(any(MetodosPago.class))).thenReturn(metodoGuardado);
        when(validaciones.convertirADTO(metodoGuardado)).thenReturn(dtoGuardado);

        // When
        MetodosPagoDTO resultado = service.guardar(datosEntrada);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(10L, resultado.getIdSucursal());
        verify(repo, times(1)).save(any(MetodosPago.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerPorId, Then retorna DTO")
    void givenIdExistente_whenTraerPorId_thenRetornaDTO() {
        // Given
        Long id = 2L;
        MetodosPago metodoBD = new MetodosPago();
        metodoBD.setId(id);

        MetodosPagoDTO dto = new MetodosPagoDTO();
        dto.setId(id);

        when(repo.findById(id)).thenReturn(Optional.of(metodoBD));
        when(validaciones.convertirADTO(metodoBD)).thenReturn(dto);

        // When
        MetodosPagoDTO resultado = service.traerPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(repo, times(1)).findById(id);
    }

    @Test
    @DisplayName("Given registros en BD, When traerTodos, Then retorna lista de DTOs iterando con for")
    void givenRegistros_whenTraerTodos_thenRetornaLista() {
        // Given
        MetodosPago m1 = new MetodosPago();
        MetodosPago m2 = new MetodosPago();

        when(repo.findAll()).thenReturn(Arrays.asList(m1, m2));
        when(validaciones.convertirADTO(any(MetodosPago.class))).thenReturn(new MetodosPagoDTO());

        // When
        List<MetodosPagoDTO> resultado = service.traerTodos();

        // Then
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Given ID existente, When cambiarEstado, Then actualiza y retorna")
    void givenIdExistente_whenCambiarEstado_thenActualizaYRetorna() {
        // Given
        Long id = 3L;
        MetodosPago metodoBD = new MetodosPago();
        metodoBD.setId(id);
        metodoBD.setActivo(true);

        MetodosPagoDTO dtoModificado = new MetodosPagoDTO();
        dtoModificado.setId(id);
        when(repo.findById(id)).thenReturn(Optional.of(metodoBD));
        when(repo.save(any(MetodosPago.class))).thenReturn(metodoBD);
        when(validaciones.convertirADTO(metodoBD)).thenReturn(dtoModificado);

        // When
        MetodosPagoDTO resultado = service.cambiarEstado(id, false);

        // Then
        assertNotNull(resultado);
        assertEquals(false, metodoBD.getActivo());
        verify(repo, times(1)).save(metodoBD);
    }

    @Test
    @DisplayName("Given ID inexistente, When eliminar, Then lanza RuntimeException")
    void givenIdInexistente_whenEliminar_thenLanzaException() {
        // Given
        Long id = 999L;
        when(repo.findById(id)).thenReturn(Optional.empty());

        // When + Then
        RuntimeException excepcion = assertThrows(RuntimeException.class,
                () -> service.eliminar(id));

        assertEquals("no existe el registro con id: " + id, excepcion.getMessage());
        verify(repo, times(0)).delete(any(MetodosPago.class));
    }
}
