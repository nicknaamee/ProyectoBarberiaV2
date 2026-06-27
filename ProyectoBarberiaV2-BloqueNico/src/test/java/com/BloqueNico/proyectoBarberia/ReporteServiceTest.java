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
    void TesteoTraerPorId() {
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

    @Test
    void testeoObtenerTodos() {
        Reporte reporte = new Reporte();
        reporte.setIdDelReporte(1L);
        reporte.setTipoDeReporte("Mensual");

        when(reporteRepository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> resultado = reporteService.traerTodosLosReportes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Mensual", resultado.get(0).getTipoDeReporte());
        verify(reporteRepository, times(1)).findAll();
    }

    @Test
    void testeoActualizar() {
        Long id = 1L;
        Reporte existente = new Reporte();
        existente.setIdDelReporte(id);
        existente.setTipoDeReporte("Diario");

        ReporteDTO nuevosDatos = new ReporteDTO();
        nuevosDatos.setTipoDeReporte("Mensual");
        nuevosDatos.setTotalDeIngresosCalculado(500000.0);
        nuevosDatos.setFechaDelReporte(LocalDate.parse("2026-06-27"));
        nuevosDatos.setResumenDelReporte("Resumen actualizado");

        when(reporteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(reporteRepository.save(any(Reporte.class))).thenReturn(existente);

        Reporte resultado = reporteService.actualizarReporte(id, nuevosDatos);

        assertNotNull(resultado);
        assertEquals("Mensual", resultado.getTipoDeReporte());
        assertEquals(500000.0, resultado.getTotalDeIngresosCalculado());
        verify(reporteRepository, times(1)).findById(id);
        verify(reporteRepository, times(1)).save(any(Reporte.class));
    }
    @Test
    void testeoEliminar() {
        Long id = 1L;
        Reporte reporte = new Reporte();
        reporte.setIdDelReporte(id);
        reporte.setTipoDeReporte("Mensual");

        when(reporteRepository.findById(id)).thenReturn(Optional.of(reporte));

        String resultado = reporteService.eliminarReporte(id);

        assertEquals("El reporte de tipo 'Mensual' ha sido eliminado", resultado);
        verify(reporteRepository, times(1)).findById(id);
        verify(reporteRepository, times(1)).delete(reporte);
    }

    @Test
    void testeoConveritrDTO() {
        Reporte reporte = new Reporte();
        reporte.setIdDelReporte(1L);
        reporte.setTipoDeReporte("Mensual");
        reporte.setTotalDeIngresosCalculado(450000.0);
        reporte.setFechaDelReporte(LocalDate.parse("2026-06-27"));
        reporte.setResumenDelReporte("Reporte de prueba");

        ReporteDTO resultado = reporteService.convertirAReporteDTO(reporte);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdDelReporte());
        assertEquals("Mensual", resultado.getTipoDeReporte());
        assertEquals(450000.0, resultado.getTotalDeIngresosCalculado());
        assertEquals(LocalDate.parse("2026-06-27"), resultado.getFechaDelReporte());
        assertEquals("Reporte de prueba", resultado.getResumenDelReporte());
    }
}