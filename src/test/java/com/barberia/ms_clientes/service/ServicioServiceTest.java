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

import com.barberia.ms_clientes.model.Servicio;
import com.barberia.ms_clientes.repository.ServicioRepository;

@ExtendWith(MockitoExtension.class)
public class ServicioServiceTest {

    @InjectMocks
    private ServicioService servicioService;

    @Mock
    private ServicioRepository servicioRepository;

    private Servicio createServicio() {
        return new Servicio(1L, "Corte de pelo", 15.0, 30);
    }

    @Test
    public void testFindAll() {
        when(servicioRepository.findAll()).thenReturn(List.of(createServicio()));
        var servicios = servicioService.traerTodos();
        assertNotNull(servicios);
        assertEquals(1, servicios.size());
    }

    @Test
    public void testFindById() {
        when(servicioRepository.findById(1L)).thenReturn(Optional.of(createServicio()));
        var servicio = servicioService.traerPorId(1L);
        assertNotNull(servicio);
        assertEquals("Corte de pelo", servicio.getNombreDelServicio());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(servicioRepository).deleteById(1L);
        servicioService.eliminar(1L);
        verify(servicioRepository, times(1)).deleteById(1L);
    }
}
