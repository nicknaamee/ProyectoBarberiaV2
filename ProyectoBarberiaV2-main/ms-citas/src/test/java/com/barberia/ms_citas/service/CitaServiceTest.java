package com.barberia.ms_citas.service;

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

import com.barberia.ms_citas.dto.CitaDTO;
import com.barberia.ms_citas.model.Cita;
import com.barberia.ms_citas.repository.CitaRepository;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaValidaciones citaValidaciones;

    @InjectMocks
    private CitaService citaService;

    @Test
    @DisplayName("Given cita válida, When agendarCita, Then guarda y retorna DTO con estado Pendiente")
    void givenCitaValida_whenAgendarCita_thenRetornaDTOPendiente() {
        // Given
        Cita nuevaCita = new Cita();
        nuevaCita.setIdBarbero(1L);
        nuevaCita.setIdCliente(2L);
        nuevaCita.setIdServicio(3L);

        Cita guardada = new Cita();
        guardada.setIdCita(1L);
        guardada.setEstadoCita("Pendiente");

        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setIdCita(1L);
        citaDTO.setEstadoCita("Pendiente");

        when(citaValidaciones.validarNullVacio(any(Cita.class))).thenReturn(true);
        when(citaRepository.save(any(Cita.class))).thenReturn(guardada);
        when(citaValidaciones.convertirADTO(guardada)).thenReturn(citaDTO);

        // When
        CitaDTO resultado = citaService.agendarCita(nuevaCita);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCita());
        assertEquals("Pendiente", resultado.getEstadoCita());
        verify(citaRepository, times(1)).save(any(Cita.class));
    }

    @Test
    @DisplayName("Given ID existente, When buscarPorId, Then retorna DTO")
    void givenIdExistente_whenBuscarPorId_thenRetornaDTO() {
        // Given
        Long idCita = 1L;
        Cita citaBD = new Cita();
        citaBD.setIdCita(idCita);

        CitaDTO citaDTO = new CitaDTO();
        citaDTO.setIdCita(idCita);

        when(citaRepository.findById(idCita)).thenReturn(Optional.of(citaBD));
        when(citaValidaciones.convertirADTO(citaBD)).thenReturn(citaDTO);

        // When
        CitaDTO resultado = citaService.buscarPorId(idCita);

        // Then
        assertNotNull(resultado);
        assertEquals(idCita, resultado.getIdCita());
        verify(citaRepository, times(1)).findById(idCita);
    }

    @Test
    @DisplayName("Given ID inexistente, When buscarPorId, Then lanza RuntimeException")
    void givenIdInexistente_whenBuscarPorId_thenLanzaException() {
        // Given
        Long idInexistente = 999L;
        when(citaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // When + Then
        RuntimeException excepcion = assertThrows(RuntimeException.class,
                () -> citaService.buscarPorId(idInexistente));

        assertEquals("No existe Cita con ese ID", excepcion.getMessage());
        verify(citaRepository, times(1)).findById(idInexistente);
    }
}
