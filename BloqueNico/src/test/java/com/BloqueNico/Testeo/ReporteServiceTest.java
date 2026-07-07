package com.BloqueNico.Testeo;

import java.time.LocalDate;
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
    @DisplayName("Given reporte válido, When guardarNuevoReporte, Then retorna entidad con ID")
    void TesteoGuardarReporte() {
        ReporteDTO dtoEntrada = new ReporteDTO();
        dtoEntrada.setTipoDeReporte("Mensual");
        dtoEntrada.setTotalDeIngresosCalculado(1000.0);
        dtoEntrada.setFechaDelReporte(LocalDate.now());
        dtoEntrada.setResumenDelReporte("Buen mes");

        Reporte reporteGuardado = new Reporte();
        reporteGuardado.setIdDelReporte(1L);
        reporteGuardado.setTipoDeReporte("Mensual");
        reporteGuardado.setTotalDeIngresosCalculado(1000.0);

        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporteGuardado);

        Reporte resultado = reporteService.guardarNuevoReporte(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdDelReporte());
        assertEquals("Mensual", resultado.getTipoDeReporte());
        verify(reporteRepository, times(1)).save(any(Reporte.class));
    }

    @Test
    @DisplayName("Given ID existente, When traerReportePorId, Then retorna entidad")
    void TesteoTraerPorID() {
        Long idBusqueda = 2L;
        Reporte reporteBD = new Reporte();
        reporteBD.setIdDelReporte(idBusqueda);
        reporteBD.setTipoDeReporte("Anual");

        when(reporteRepository.findById(idBusqueda)).thenReturn(Optional.of(reporteBD));

        Reporte resultado = reporteService.traerReportePorId(idBusqueda);

        assertNotNull(resultado);
        assertEquals(idBusqueda, resultado.getIdDelReporte());
        assertEquals("Anual", resultado.getTipoDeReporte());
        verify(reporteRepository, times(1)).findById(idBusqueda);
    }

    @Test
    @DisplayName("Given reportes en BD, When traerTodosLosReportes, Then retorna lista")
    void TesteoTraerTodosLosReportes() {
        Reporte r1 = new Reporte();
        r1.setIdDelReporte(1L);
        r1.setTipoDeReporte("Mensual");
        Reporte r2 = new Reporte();
        r2.setIdDelReporte(2L);
        r2.setTipoDeReporte("Anual");

        when(reporteRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Reporte> resultado = reporteService.traerTodosLosReportes();

        assertEquals(2, resultado.size());
        assertEquals("Mensual", resultado.get(0).getTipoDeReporte());
        verify(reporteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Given reporte existente, When eliminarReporte, Then retorna mensaje de éxito y llama a delete")
    void TesteoEliminarReporte() {
        Long idEliminar = 3L;
        Reporte r = new Reporte();
        r.setIdDelReporte(idEliminar);
        r.setTipoDeReporte("Semanal");
        when(reporteRepository.findById(idEliminar)).thenReturn(Optional.of(r));

        String resultado = reporteService.eliminarReporte(idEliminar);

        assertNotNull(resultado);
        assertEquals("El reporte de tipo 'Semanal' ha sido eliminado", resultado);
        verify(reporteRepository, times(1)).delete(r);
    }

    @Test
    @DisplayName("Given reporte existente, When actualizarReporte, Then retorna entidad actualizada")
    void TesteoActualizarReporte() {
        Long idActualizar = 1L;
        Reporte existente = new Reporte();
        existente.setIdDelReporte(idActualizar);
        existente.setTipoDeReporte("Original");

        ReporteDTO datosNuevos = new ReporteDTO();
        datosNuevos.setTipoDeReporte("Modificado");
        datosNuevos.setTotalDeIngresosCalculado(500.0);

        when(reporteRepository.findById(idActualizar)).thenReturn(Optional.of(existente));
        when(reporteRepository.save(any(Reporte.class))).thenAnswer(inv -> inv.getArgument(0));

        Reporte resultado = reporteService.actualizarReporte(idActualizar, datosNuevos);

        assertNotNull(resultado);
        assertEquals("Modificado", resultado.getTipoDeReporte());
        assertEquals(500.0, resultado.getTotalDeIngresosCalculado());
        verify(reporteRepository, times(1)).save(existente);
    }
}
