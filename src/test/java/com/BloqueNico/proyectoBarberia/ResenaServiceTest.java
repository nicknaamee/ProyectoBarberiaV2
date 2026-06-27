package com.BloqueNico.proyectoBarberia;

import java.time.LocalDate;
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

import com.BloqueNico.dto.ResenaDTO;
import com.BloqueNico.model.Barbero;
import com.BloqueNico.model.Cliente;
import com.BloqueNico.model.Resena;
import com.BloqueNico.repository.BarberoRepository;
import com.BloqueNico.repository.ClienteRepository;
import com.BloqueNico.repository.ResenaRepository;
import com.BloqueNico.service.ResenaService;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private BarberoRepository barberoRepository;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void testeoGuardarResena() {
        ResenaDTO dtoEntrada = new ResenaDTO();
        dtoEntrada.setIdDelClienteQueOpina(1L);
        dtoEntrada.setIdDelBarberoEvaluado(2L);
        dtoEntrada.setCalificacionDeEstrellas(5);
        dtoEntrada.setComentarioDelCliente("Excelente corte de cabello");
        dtoEntrada.setFechaDeLaResena(LocalDate.parse("2026-06-27"));

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Barbero barbero = new Barbero();
        barbero.setIdBarbero(2L);

        Resena resenaEsperada = new Resena();
        resenaEsperada.setIdDeLaResena(10L);
        resenaEsperada.setCliente(cliente);
        resenaEsperada.setBarbero(barbero);
        resenaEsperada.setCalificacionDeEstrellas(5);
        resenaEsperada.setComentarioDelCliente("Excelente corte de cabello");
        resenaEsperada.setFechaDeLaResena(LocalDate.parse("2026-06-27"));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(barberoRepository.findById(2L)).thenReturn(Optional.of(barbero));
        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaEsperada);

        Resena resultado = resenaService.guardarNuevaResena(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getIdDeLaResena());
        assertEquals(5, resultado.getCalificacionDeEstrellas());
        assertEquals("Excelente corte de cabello", resultado.getComentarioDelCliente());

        verify(clienteRepository, times(1)).findById(1L);
        verify(barberoRepository, times(1)).findById(2L);
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    void testeoTraerResenaPorIdExistente() {
        Long idBusqueda = 10L;
        Resena resenaBD = new Resena();
        resenaBD.setIdDeLaResena(idBusqueda);
        resenaBD.setCalificacionDeEstrellas(4);

        when(resenaRepository.findById(idBusqueda)).thenReturn(Optional.of(resenaBD));

        Resena resultado = resenaService.traerResenaPorId(idBusqueda);

        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdDeLaResena());
        assertEquals(4, resultado.getCalificacionDeEstrellas());

        verify(resenaRepository, times(1)).findById(idBusqueda);
    }

}