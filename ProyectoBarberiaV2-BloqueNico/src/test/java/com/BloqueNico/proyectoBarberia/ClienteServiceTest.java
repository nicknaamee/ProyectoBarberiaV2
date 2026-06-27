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
    }

    @Test
    void testeoObtenerTodos() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombreCliente("Nico");
        cliente.setApellidoCliente("Poblete");

        when(repositorioDeClientes.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO> resultado = clienteService.traerTodosLosClientes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Nico", resultado.get(0).getNombreCliente());
        verify(repositorioDeClientes, times(1)).findAll();
    }

    @Test
    void testeoActualizarCliente() {
        Long id = 1L;
        Cliente existente = new Cliente();
        existente.setIdCliente(id);
        existente.setNombreCliente("Carlos");

        ClienteDTO nuevosDatos = new ClienteDTO();
        nuevosDatos.setNombreCliente("Nico");
        nuevosDatos.setApellidoCliente("Poblete");
        nuevosDatos.setEmailCliente("nico@correo.com");
        nuevosDatos.setTelefonoCliente("+56911112222");

        when(repositorioDeClientes.findById(id)).thenReturn(Optional.of(existente));
        when(repositorioDeClientes.save(any(Cliente.class))).thenReturn(existente);

        ClienteDTO resultado = clienteService.actualizarCliente(id, nuevosDatos);

        assertNotNull(resultado);
        assertEquals("Nico", resultado.getNombreCliente());
        assertEquals("Poblete", resultado.getApellidoCliente());
        verify(repositorioDeClientes, times(1)).findById(id);
        verify(repositorioDeClientes, times(1)).save(any(Cliente.class));
    }

    @Test
    void testeoEliminar() {
        Long id = 1L;
        when(repositorioDeClientes.existsById(id)).thenReturn(true);
        doNothing().when(repositorioDeClientes).deleteById(id);

        clienteService.eliminarCliente(id);

        verify(repositorioDeClientes, times(1)).existsById(id);
        verify(repositorioDeClientes, times(1)).deleteById(id);
    }

    @Test
    void testeoConvertirDTO() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNombreCliente("Nico");
        cliente.setApellidoCliente("Poblete");
        cliente.setEmailCliente("nico@correo.com");
        cliente.setTelefonoCliente("+56911112222");

        ClienteDTO resultado = clienteService.convertirADTO(cliente);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCliente());
        assertEquals("Nico", resultado.getNombreCliente());
        assertEquals("Poblete", resultado.getApellidoCliente());
        assertEquals("nico@correo.com", resultado.getEmailCliente());
        assertEquals("+56911112222", resultado.getTelefonoCliente());
    }

    }