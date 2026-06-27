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

import com.BloqueNico.dto.ReporteDTO;
import com.BloqueNico.model.Reporte;
import com.BloqueNico.repository.ReporteRepository;
import com.BloqueNico.service.ReporteService;

@ExtendWith(MockitoExtension.class)
class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService; 

    @Test
    void TesteoGuardarReporte() {
        ReporteDTO dtoEntrada = new ReporteDTO();
        dtoEntrada.setTipoDeReporte("Mensual");
        dtoEntrada.setTotalDeIngresosCalculado(450000.0);
        dtoEntrada.setFechaDelReporte(LocalDate.parse("2026-06-27"));
        dtoEntrada.setResumenDelReporte("Cortes de barbería del mes");
        Reporte reporteEsperado = new Reporte();
        reporteEsperado.setIdDelReporte(1L); // ID simulado por la BD
        reporteEsperado.setTipoDeReporte("Mensual");
        reporteEsperado.setTotalDeIngresosCalculado(450000.0);
        reporteEsperado.setFechaDelReporte(LocalDate.parse("2026-06-27"));
        reporteEsperado.setResumenDelReporte("Cortes de barbería del mes");
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporteEsperado);
        Reporte resultado = reporteService.guardarNuevoReporte(dtoEntrada);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdDelReporte());
        assertEquals("Mensual", resultado.getTipoDeReporte());
        assertEquals(450000.0, resultado.getTotalDeIngresosCalculado());
        verify(reporteRepository, times(1)).save(any(Reporte.class));
    }

    @Test
    void TesteoIdExistente() {
        Long idBusqueda = 1L;
        Reporte reporteBD = new Reporte();
        reporteBD.setIdDelReporte(idBusqueda);
        reporteBD.setTipoDeReporte("Diario");
        when(reporteRepository.findById(idBusqueda)).thenReturn(Optional.of(reporteBD));
        Reporte resultado = reporteService.traerReportePorId(idBusqueda);
        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdDelReporte());
        assertEquals("Diario", resultado.getTipoDeReporte());
        verify(reporteRepository, times(1)).findById(idBusqueda);
    }
}