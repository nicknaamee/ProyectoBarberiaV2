package com.BloqueNico.proyectoBarberia;

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
    private ClienteRepository clienteRepository; 

    @InjectMocks
    private ClienteService clienteService; 

    @Test
    void cuandoGuardarNuevoCliente_entoncesRetornaClienteDTOCreado() {
        ClienteDTO dtoEntrada = new ClienteDTO();
        dtoEntrada.setNombreCliente("Carlos");
        dtoEntrada.setTelefonoCliente("912345678");
        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setIdCliente(1L); 
        clienteGuardado.setNombreCliente("Carlos");
        clienteGuardado.setTelefonoCliente("912345678");
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardado);
        ClienteDTO resultadoDTO = clienteService.guardarNuevoCliente(dtoEntrada);
        assertNotNull(resultadoDTO);
        assertEquals(1L, resultadoDTO.getIdCliente()); 
        assertEquals("Carlos", resultadoDTO.getNombreCliente());
        assertEquals("912345678", resultadoDTO.getTelefonoCliente());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }
}
