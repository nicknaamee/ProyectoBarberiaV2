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

    @Test
    void testeoObtenerTodos() {
        Barbero barbero = new Barbero();
        barbero.setNombreBarbero("Nico");
        barbero.setEspecialidadBarbero("Mullet");
        barbero.setTelefonoBarbero("+56911112222");

        when(barberoRepository.findAll()).thenReturn(List.of(barbero));

        List<BarberoDTO> resultado = barberoService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Nico", resultado.get(0).getNombreBarbero());
        verify(barberoRepository, times(1)).findAll();
    }
    @Test
    void testeoEliminar() {
        Long id = 1L;
        Barbero barbero = new Barbero();
        barbero.setIdBarbero(id);
        barbero.setNombreBarbero("Nico");

        when(barberoRepository.findById(id)).thenReturn(Optional.of(barbero));

        String resultado = barberoService.eliminar(id);

        assertEquals("El barbero 'Nico'ha sido eliminado", resultado);
        verify(barberoRepository, times(1)).findById(id);
        verify(barberoRepository, times(1)).delete(barbero);
    }

    @Test
    void testeoActualizar() {
        Long id = 1L;
        Barbero existente = new Barbero();
        existente.setIdBarbero(id);
        existente.setNombreBarbero("Carlos");

        Barbero nuevosDatos = new Barbero();
        nuevosDatos.setNombreBarbero("Nico");
        nuevosDatos.setEspecialidadBarbero("Fade");
        nuevosDatos.setTelefonoBarbero("+56933334444");

        when(barberoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(barberoRepository.save(any(Barbero.class))).thenReturn(existente);

        Barbero resultado = barberoService.actualizarBarberos(id, nuevosDatos);

        assertNotNull(resultado);
        assertEquals("Nico", resultado.getNombreBarbero());
        assertEquals("Fade", resultado.getEspecialidadBarbero());
        verify(barberoRepository, times(1)).findById(id);
        verify(barberoRepository, times(1)).save(any(Barbero.class));
    }

    @Test
    void testeoBuscarPorEspecialidad() {
        String especialidad = "Fade";
        Barbero barbero = new Barbero();
        barbero.setEspecialidadBarbero(especialidad);

        when(barberoRepository.findByEspecialidadBarbero(especialidad)).thenReturn(List.of(barbero));

        List<BarberoDTO> resultado = barberoService.buscarPorEspecialidadBarbero(especialidad);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(especialidad, resultado.get(0).getEspecialidadBarbero());
        verify(barberoRepository, times(1)).findByEspecialidadBarbero(especialidad);
    }

    @Test
    void testeoBuscarPorEstado() {
        boolean estado = true;
        Barbero barbero = new Barbero();

        when(barberoRepository.findByEstado(estado)).thenReturn(List.of(barbero));

        List<BarberoDTO> resultado = barberoService.buscarPorEstado(estado);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(barberoRepository, times(1)).findByEstado(estado);
    }

    @Test
    void testeoConcertirDTO() {
        Barbero barbero = new Barbero();
        barbero.setNombreBarbero("Nico");
        barbero.setEspecialidadBarbero("Mullet");
        barbero.setTelefonoBarbero("+56911112222");

        BarberoDTO resultado = barberoService.convertirADTO(barbero);

        assertNotNull(resultado);
        assertEquals("Nico", resultado.getNombreBarbero());
        assertEquals("Mullet", resultado.getEspecialidadBarbero());
        assertEquals("+56911112222", resultado.getTelefonoBarbero());
    }


}