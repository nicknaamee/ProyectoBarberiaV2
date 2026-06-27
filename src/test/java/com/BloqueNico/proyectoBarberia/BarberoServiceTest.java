package com.BloqueNico.proyectoBarberia;
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
    void testeoGuardarBarbero() {
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
    void testeoBuscarPorIdExistente() {
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
}