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

import com.BloqueNico.dto.ClienteDTO;
import com.BloqueNico.model.Cliente;
import com.BloqueNico.repository.ClienteRepository;
import com.BloqueNico.service.ClienteService;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repositorioDeClientes;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testeoGuardarCliente() {
        ClienteDTO dtoEntrada = new ClienteDTO();
        dtoEntrada.setNombreCliente("Nico");
        dtoEntrada.setApellidoCliente("Poblete");
        dtoEntrada.setEmailCliente("nico@correo.com");
        dtoEntrada.setTelefonoCliente("+56911112222");
        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setIdCliente(1L);
        clienteGuardado.setNombreCliente("Nico");
        clienteGuardado.setApellidoCliente("Poblete");
        clienteGuardado.setEmailCliente("nico@correo.com");
        clienteGuardado.setTelefonoCliente("+56911112222");
        when(repositorioDeClientes.save(any(Cliente.class))).thenReturn(clienteGuardado);
        ClienteDTO resultadoDTO = clienteService.guardarNuevoCliente(dtoEntrada);
        assertNotNull(resultadoDTO);
        assertEquals(1L, resultadoDTO.getIdCliente());
        assertEquals("Nico", resultadoDTO.getNombreCliente());
        assertEquals("Poblete", resultadoDTO.getApellidoCliente());
        verify(repositorioDeClientes, times(1)).save(any(Cliente.class));
    }

    @Test
    void testeoTraerClientePorIdExistente() {
        Long idBusqueda = 10L;
        Cliente clienteBD = new Cliente();
        clienteBD.setIdCliente(idBusqueda);
        clienteBD.setNombreCliente("Carlos");
        clienteBD.setApellidoCliente("Caszely");
        when(repositorioDeClientes.findById(idBusqueda)).thenReturn(Optional.of(clienteBD));
        ClienteDTO resultadoDTO = clienteService.traerClientePorId(idBusqueda);
        assertNotNull(resultadoDTO);
        assertEquals(idBusqueda, resultadoDTO.getIdCliente());
        assertEquals("Carlos", resultadoDTO.getNombreCliente());
        verify(repositorioDeClientes, times(1)).findById(idBusqueda);
    }}