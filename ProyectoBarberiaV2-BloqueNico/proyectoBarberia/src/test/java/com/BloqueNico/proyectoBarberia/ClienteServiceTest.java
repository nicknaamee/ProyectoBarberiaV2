package com.BloqueNico.proyectoBarberia;

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

import com.BloqueNico.proyectoBarberia.dto.ClienteDTO;
import com.BloqueNico.proyectoBarberia.model.Cliente;
import com.BloqueNico.proyectoBarberia.repository.ClienteRepository;
import com.BloqueNico.proyectoBarberia.service.ClienteService;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repositorioDeClientes;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("Given cliente válido, When guardarNuevoCliente, Then retorna DTO con ID generado")
    void givenClienteValido_whenGuardar_thenRetornaDTOConId() {
        // Given
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

        // When
        ClienteDTO resultadoDTO = clienteService.guardarNuevoCliente(dtoEntrada);

        // Then
        assertNotNull(resultadoDTO);
        assertEquals(1L, resultadoDTO.getIdCliente());
        assertEquals("Nico", resultadoDTO.getNombreCliente());
        assertEquals("Poblete", resultadoDTO.getApellidoCliente());
        verify(repositorioDeClientes, times(1)).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerClientePorId, Then retorna DTO correcto")
    void givenIdExistente_whenTraerPorId_thenRetornaDTO() {
        // Given
        Long idBusqueda = 10L;
        Cliente clienteBD = new Cliente();
        clienteBD.setIdCliente(idBusqueda);
        clienteBD.setNombreCliente("Carlos");
        clienteBD.setApellidoCliente("Caszely");

        when(repositorioDeClientes.findById(idBusqueda)).thenReturn(Optional.of(clienteBD));

        // When
        ClienteDTO resultadoDTO = clienteService.traerClientePorId(idBusqueda);

        // Then
        assertNotNull(resultadoDTO);
        assertEquals(idBusqueda, resultadoDTO.getIdCliente());
        assertEquals("Carlos", resultadoDTO.getNombreCliente());
        verify(repositorioDeClientes, times(1)).findById(idBusqueda);
    }

    @Test
    @DisplayName("Given ID inexistente, When traerClientePorId, Then lanza RuntimeException")
    void givenIdInexistente_whenTraerPorId_thenLanzaException() {
        // Given
        Long idInexistente = 999L;
        when(repositorioDeClientes.findById(idInexistente)).thenReturn(Optional.empty());

        // When + Then
        assertThrows(RuntimeException.class, () -> clienteService.traerClientePorId(idInexistente));
        verify(repositorioDeClientes, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Given clientes en BD, When traerTodos, Then retorna lista de DTOs")
    void givenClientesEnBD_whenTraerTodos_thenRetornaListaDTO() {
        // Given
        Cliente c1 = new Cliente();
        c1.setIdCliente(1L);
        c1.setNombreCliente("Ana");
        Cliente c2 = new Cliente();
        c2.setIdCliente(2L);
        c2.setNombreCliente("Luis");

        when(repositorioDeClientes.findAll()).thenReturn(Arrays.asList(c1, c2));

        // When
        List<ClienteDTO> resultado = clienteService.traerTodosLosClientes();

        // Then
        assertEquals(2, resultado.size());
        assertEquals("Ana", resultado.get(0).getNombreCliente());
        verify(repositorioDeClientes, times(1)).findAll();
    }

    @Test
    @DisplayName("Given cliente existente, When eliminar, Then se ejecuta deleteById sin error")
    void givenClienteExistente_whenEliminar_thenDeleteSinError() {
        // Given
        Long idEliminar = 5L;
        when(repositorioDeClientes.existsById(idEliminar)).thenReturn(true);

        // When
        clienteService.eliminarCliente(idEliminar);

        // Then
        verify(repositorioDeClientes, times(1)).deleteById(idEliminar);
    }

    @Test
    @DisplayName("Given ID inexistente, When eliminar, Then lanza RuntimeException")
    void givenIdInexistente_whenEliminar_thenLanzaException() {
        // Given
        Long idInexistente = 999L;
        when(repositorioDeClientes.existsById(idInexistente)).thenReturn(false);

        // When + Then
        assertThrows(RuntimeException.class, () -> clienteService.eliminarCliente(idInexistente));
        verify(repositorioDeClientes, times(0)).deleteById(idInexistente);
    }

    @Test
    @DisplayName("Given cliente existente, When actualizar, Then retorna DTO con datos modificados")
    void givenClienteExistente_whenActualizar_thenRetornaDTOActualizado() {
        // Given
        Long idActualizar = 1L;
        Cliente existente = new Cliente();
        existente.setIdCliente(idActualizar);
        existente.setNombreCliente("Original");

        ClienteDTO datosNuevos = new ClienteDTO();
        datosNuevos.setNombreCliente("Modificado");
        datosNuevos.setApellidoCliente("Apellido");
        datosNuevos.setEmailCliente("mod@correo.com");
        datosNuevos.setTelefonoCliente("+56900000000");

        when(repositorioDeClientes.findById(idActualizar)).thenReturn(Optional.of(existente));
        when(repositorioDeClientes.save(any(Cliente.class))).thenAnswer(inv -> inv.getArgument(0));

        // When
        ClienteDTO resultado = clienteService.actualizarCliente(idActualizar, datosNuevos);

        // Then
        assertNotNull(resultado);
        assertEquals("Modificado", resultado.getNombreCliente());
        verify(repositorioDeClientes, times(1)).save(existente);
    }
}
