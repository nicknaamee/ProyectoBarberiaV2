package com.barberia.ms_clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.ReporteDTO;
import com.barberia.ms_clientes.model.Reporte;
import com.barberia.ms_clientes.repository.ReporteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;


    public List<Reporte> traerTodosLosReportes() {
        log.info("Iniciando consulta de todos los reportes en la base de datos");
        return reporteRepository.findAll();
    }

    public Reporte traerReportePorId(Long id) {
        log.info("Buscando reporte con ID: {}", id);
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el reporte con ID: " + id));
    }

    public Reporte guardarNuevoReporte(ReporteDTO datos) {
        log.info("Generando nuevo reporte de tipo: {}", datos.getTipoDeReporte());
        
        Reporte nuevoReporte = new Reporte();
        nuevoReporte.setTipoDeReporte(datos.getTipoDeReporte());
        nuevoReporte.setTotalDeIngresosCalculado(datos.getTotalDeIngresosCalculado());
        nuevoReporte.setFechaDelReporte(datos.getFechaDelReporte());
        nuevoReporte.setResumenDelReporte(datos.getResumenDelReporte());
        
        return reporteRepository.save(nuevoReporte);
    }


    public Reporte actualizarReporte(Long id, ReporteDTO datosNuevos) {
        log.info("Actualizando reporte con ID: {}", id);
        
        Reporte reporteExistente = traerReportePorId(id);
        
        reporteExistente.setTipoDeReporte(datosNuevos.getTipoDeReporte());
        reporteExistente.setTotalDeIngresosCalculado(datosNuevos.getTotalDeIngresosCalculado());
        reporteExistente.setFechaDelReporte(datosNuevos.getFechaDelReporte());
        reporteExistente.setResumenDelReporte(datosNuevos.getResumenDelReporte());
        
        return reporteRepository.save(reporteExistente);
    }


    public String eliminarReporte(Long id) {
        try {
            Reporte reporte = reporteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No existe reporte con ese ID"));
            reporteRepository.delete(reporte);
            return "El reporte de tipo '" + reporte.getTipoDeReporte() + "' ha sido eliminado";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }


    public ReporteDTO convertirAReporteDTO(Reporte reporte) {
        ReporteDTO reporteDTO = new ReporteDTO();
        reporteDTO.setIdDelReporte(reporte.getIdDelReporte());
        reporteDTO.setTipoDeReporte(reporte.getTipoDeReporte());
        reporteDTO.setTotalDeIngresosCalculado(reporte.getTotalDeIngresosCalculado());
        reporteDTO.setFechaDelReporte(reporte.getFechaDelReporte());
        reporteDTO.setResumenDelReporte(reporte.getResumenDelReporte());
        return reporteDTO;
    }
}