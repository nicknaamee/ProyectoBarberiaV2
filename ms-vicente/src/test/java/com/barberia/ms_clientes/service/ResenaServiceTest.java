package com.barberia.ms_clientes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.repository.ResenaRepository;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @InjectMocks
    private ResenaService resenaService;

    @Mock
    private ResenaRepository resenaRepository;

    private Resena createResena() {
        return new Resena(1L, 10L, 5L, 4, "Muy buen corte", java.time.LocalDate.now());
    }

    @Test
    public void testFindAll() {
        when(resenaRepository.findAll()).thenReturn(List.of(createResena()));
        var resenas = resenaService.traerTodos();
        assertNotNull(resenas);
        assertEquals(1, resenas.size());
    }

    @Test
    public void testFindById() {
        when(resenaRepository.findById(1L)).thenReturn(Optional.of(createResena()));
        var resena = resenaService.traerPorId(1L);
        assertNotNull(resena);
        assertEquals("Muy buen corte", resena.getComentarioDelCliente());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(resenaRepository).deleteById(1L);
        resenaService.eliminar(1L);
        verify(resenaRepository, times(1)).deleteById(1L);
    }
}
