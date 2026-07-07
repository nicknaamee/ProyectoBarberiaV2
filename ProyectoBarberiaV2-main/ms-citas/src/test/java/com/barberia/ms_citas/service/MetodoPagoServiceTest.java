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

import com.barberia.ms_citas.dto.MetodoPagoDTO;
import com.barberia.ms_citas.model.MetodoPago;
import com.barberia.ms_citas.repository.MetodoPagoRepository;

@ExtendWith(MockitoExtension.class)
class MetodoPagoServiceTest {

    @Mock
    private MetodoPagoRepository repo;

    @Mock
    private MetodoPagoValidaciones validaciones;

    @InjectMocks
    private MetodoPagoService service;

    @Test
    @DisplayName("Given datos válidos, When guardar, Then retorna DTO guardado")
    void givenDatosValidos_whenGuardar_thenRetornaDTO() {
        // Given
        MetodoPagoDTO datosEntrada = new MetodoPagoDTO();
        datosEntrada.setNombre("Efectivo");
        datosEntrada.setActivo(true);

        MetodoPago metodoGuardado = new MetodoPago();
        metodoGuardado.setId(1L);
        metodoGuardado.setNombre("Efectivo");
        metodoGuardado.setActivo(true);

        MetodoPagoDTO dtoGuardado = new MetodoPagoDTO();
        dtoGuardado.setId(1L);
        dtoGuardado.setNombre("Efectivo");
        dtoGuardado.setActivo(true);

        when(validaciones.validarNullVacio(datosEntrada)).thenReturn(true);
        when(repo.save(any(MetodoPago.class))).thenReturn(metodoGuardado);
        when(validaciones.convertirADTO(metodoGuardado)).thenReturn(dtoGuardado);

        // When
        MetodoPagoDTO resultado = service.guardar(datosEntrada);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Efectivo", resultado.getNombre());
        verify(repo, times(1)).save(any(MetodoPago.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerPorId, Then retorna DTO")
    void givenIdExistente_whenTraerPorId_thenRetornaDTO() {
        // Given
        Long id = 1L;
        MetodoPago metodoBD = new MetodoPago();
        metodoBD.setId(id);
        metodoBD.setNombre("Tarjeta");

        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setId(id);
        dto.setNombre("Tarjeta");

        when(repo.findById(id)).thenReturn(Optional.of(metodoBD));
        when(validaciones.convertirADTO(metodoBD)).thenReturn(dto);

        // When
        MetodoPagoDTO resultado = service.traerPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Tarjeta", resultado.getNombre());
        verify(repo, times(1)).findById(id);
    }

    @Test
    @DisplayName("Given registros en BD, When traerTodos, Then retorna lista de DTOs usando bucle")
    void givenRegistros_whenTraerTodos_thenRetornaLista() {
        // Given
        MetodoPago m1 = new MetodoPago();
        m1.setId(1L);
        MetodoPago m2 = new MetodoPago();
        m2.setId(2L);

        MetodoPagoDTO d1 = new MetodoPagoDTO();
        MetodoPagoDTO d2 = new MetodoPagoDTO();

        when(repo.findAll()).thenReturn(Arrays.asList(m1, m2));
        when(validaciones.convertirADTO(m1)).thenReturn(d1);
        when(validaciones.convertirADTO(m2)).thenReturn(d2);

        // When
        List<MetodoPagoDTO> resultado = service.traerTodos();

        // Then
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Given ID existente, When cambiarEstado, Then actualiza y retorna DTO")
    void givenIdExistente_whenCambiarEstado_thenActualizaYRetorna() {
        // Given
        Long id = 1L;
        MetodoPago metodoBD = new MetodoPago();
        metodoBD.setId(id);
        metodoBD.setActivo(true);

        MetodoPagoDTO dtoModificado = new MetodoPagoDTO();
        dtoModificado.setId(id);
        dtoModificado.setActivo(false);

        when(repo.findById(id)).thenReturn(Optional.of(metodoBD));
        when(repo.save(metodoBD)).thenReturn(metodoBD); // BD salva el objeto modificado
        when(validaciones.convertirADTO(metodoBD)).thenReturn(dtoModificado);

        // When
        MetodoPagoDTO resultado = service.cambiarEstado(id, false);

        // Then
        assertNotNull(resultado);
        assertEquals(false, resultado.getActivo());
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

        assertEquals("no existe el metodo de pago con id: " + id, excepcion.getMessage());
        verify(repo, times(0)).delete(any(MetodoPago.class));
    }
}
