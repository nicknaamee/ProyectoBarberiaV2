package com.BloqueNico.Testeo;

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
    @DisplayName("Given datos válidos, When guardarNuevaResena, Then retorna entidad guardada")
    void TesteoGuardarReseña() {
        ResenaDTO dtoEntrada = new ResenaDTO();
        dtoEntrada.setIdDelClienteQueOpina(1L);
        dtoEntrada.setIdDelBarberoEvaluado(2L);
        dtoEntrada.setCalificacionDeEstrellas(5);
        dtoEntrada.setComentarioDelCliente("Excelente");

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);

        Barbero barbero = new Barbero();
        barbero.setIdBarbero(2L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(barberoRepository.findById(2L)).thenReturn(Optional.of(barbero));

        Resena resenaGuardada = new Resena();
        resenaGuardada.setIdDeLaResena(10L);
        resenaGuardada.setCliente(cliente);
        resenaGuardada.setBarbero(barbero);
        resenaGuardada.setCalificacionDeEstrellas(5);
        resenaGuardada.setComentarioDelCliente("Excelente");

        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaGuardada);

        Resena resultado = resenaService.guardarNuevaResena(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getIdDeLaResena());
        assertEquals(5, resultado.getCalificacionDeEstrellas());
        verify(clienteRepository, times(1)).findById(1L);
        verify(barberoRepository, times(1)).findById(2L);
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerResenaPorId, Then retorna entidad")
    void TesteoTraerPorID() {
        Long idBusqueda = 5L;
        Resena resenaBD = new Resena();
        resenaBD.setIdDeLaResena(idBusqueda);
        resenaBD.setComentarioDelCliente("Muy bueno");

        when(resenaRepository.findById(idBusqueda)).thenReturn(Optional.of(resenaBD));

        Resena resultado = resenaService.traerResenaPorId(idBusqueda);

        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdDeLaResena());
        assertEquals("Muy bueno", resultado.getComentarioDelCliente());
        verify(resenaRepository, times(1)).findById(idBusqueda);
    }

    @Test
    @DisplayName("Given resenas en BD, When traerTodasLasResenas, Then retorna lista")
    void TesteoTraerTodasLasResenas() {
        Resena r1 = new Resena();
        r1.setIdDeLaResena(1L);
        Resena r2 = new Resena();
        r2.setIdDeLaResena(2L);

        when(resenaRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Resena> resultado = resenaService.traerTodasLasResenas();

        assertEquals(2, resultado.size());
        verify(resenaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given resena existente, When eliminarResena, Then llama a deleteById")
    void TesteoEliminar() {
        Long idEliminar = 3L;
        Resena r = new Resena();
        r.setIdDeLaResena(idEliminar);
        when(resenaRepository.findById(idEliminar)).thenReturn(Optional.of(r));

        resenaService.eliminarResena(idEliminar);

        verify(resenaRepository, times(1)).deleteById(idEliminar);
    }
}
