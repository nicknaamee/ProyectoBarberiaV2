package com.barberia.ms_citas.service;

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

import com.barberia.ms_citas.dto.FacturaDTO;
import com.barberia.ms_citas.dto.ServicioExternoDTO;
import com.barberia.ms_citas.model.Cita;
import com.barberia.ms_citas.model.Factura;
import com.barberia.ms_citas.repository.CitaRepository;
import com.barberia.ms_citas.repository.FacturaRepository;

@ExtendWith(MockitoExtension.class)
class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private FacturaValidaciones facturaValidaciones;

    @InjectMocks
    private FacturaService facturaService;

    @Test
    @DisplayName("Given cita válida sin facturar, When generarFactura, Then guarda factura correctamente")
    void givenCitaValida_whenGenerarFactura_thenGuardaYRetornaDTO() {
        // Given
        Long idCita = 1L;
        Cita citaBD = new Cita();
        citaBD.setIdCita(idCita);
        citaBD.setIdServicio(10L);

        ServicioExternoDTO servicioExterno = new ServicioExternoDTO();
        servicioExterno.setPrecioDelServicio(15000.0);

        Factura facturaGuardada = new Factura();
        facturaGuardada.setIdFactura(100L);
        facturaGuardada.setMontoTotal(15000.0);

        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setIdFactura(100L);
        facturaDTO.setMontoTotal(15000.0);

        when(citaRepository.findById(idCita)).thenReturn(Optional.of(citaBD));
        when(facturaValidaciones.obtenerServicio(10L)).thenReturn(servicioExterno);
        when(facturaRepository.findByCitaIdCita(idCita)).thenReturn(Optional.empty());
        when(facturaRepository.save(any(Factura.class))).thenReturn(facturaGuardada);
        when(facturaValidaciones.convertirADTO(facturaGuardada)).thenReturn(facturaDTO);

        // When
        FacturaDTO resultado = facturaService.generarFactura(idCita, "Efectivo");

        // Then
        assertNotNull(resultado);
        assertEquals(15000.0, resultado.getMontoTotal());
        verify(facturaRepository, times(1)).save(any(Factura.class));
    }

    @Test
    @DisplayName("Given cita ya facturada, When generarFactura, Then lanza RuntimeException")
    void givenCitaYaFacturada_whenGenerarFactura_thenLanzaException() {
        // Given
        Long idCita = 2L;
        Cita citaBD = new Cita();
        citaBD.setIdCita(idCita);

        ServicioExternoDTO servicioExterno = new ServicioExternoDTO();
        servicioExterno.setPrecioDelServicio(20000.0);

        when(citaRepository.findById(idCita)).thenReturn(Optional.of(citaBD));
        when(facturaValidaciones.obtenerServicio(any())).thenReturn(servicioExterno);

        when(facturaRepository.findByCitaIdCita(idCita)).thenReturn(Optional.of(new Factura()));

        // When + Then
        RuntimeException excepcion = assertThrows(RuntimeException.class,
                () -> facturaService.generarFactura(idCita, "Tarjeta"));

        assertEquals("Esta cita ya fue facturada anteriormente", excepcion.getMessage());
        verify(facturaRepository, times(0)).save(any(Factura.class));
    }

    @Test
    @DisplayName("Given servicio offline, When generarFactura, Then guarda factura con monto 0.0")
    void givenServicioOffline_whenGenerarFactura_thenGuardaMontoCero() {
        // Given
        Long idCita = 3L;
        Cita citaBD = new Cita();
        citaBD.setIdCita(idCita);
        citaBD.setIdServicio(99L);

        ServicioExternoDTO servicioOffline = new ServicioExternoDTO();
        servicioOffline.setPrecioDelServicio(0.0);

        Factura facturaGuardada = new Factura();
        facturaGuardada.setMontoTotal(0.0);

        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setMontoTotal(0.0);

        when(citaRepository.findById(idCita)).thenReturn(Optional.of(citaBD));
        when(facturaValidaciones.obtenerServicio(99L)).thenReturn(servicioOffline);
        when(facturaRepository.findByCitaIdCita(idCita)).thenReturn(Optional.empty());
        when(facturaRepository.save(any(Factura.class))).thenReturn(facturaGuardada);
        when(facturaValidaciones.convertirADTO(facturaGuardada)).thenReturn(facturaDTO);

        // When
        FacturaDTO resultado = facturaService.generarFactura(idCita, "Transferencia");

        // Then
        assertNotNull(resultado);
        assertEquals(0.0, resultado.getMontoTotal());
        verify(facturaRepository, times(1)).save(any(Factura.class));
    }
}
