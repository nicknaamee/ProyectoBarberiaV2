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
    void cuandoGuardarBarbero_entoncesRetornaBarberoGuardado() {
        Barbero barberoPrueba = new Barbero();
        barberoPrueba.setNombreBarbero("Nico");
        barberoPrueba.setEspecialidadBarbero("Mullet");
        when(barberoRepository.save(any(Barbero.class))).thenReturn(barberoPrueba);
        Barbero resultado = barberoService.guardarBarbero(barberoPrueba);
        assertNotNull(resultado); 
        assertEquals("Nico", resultado.getNombreBarbero()); 
        assertEquals("Mullet", resultado.getEspecialidadBarbero());
        verify(barberoRepository, times(1)).save(barberoPrueba);
    }
}