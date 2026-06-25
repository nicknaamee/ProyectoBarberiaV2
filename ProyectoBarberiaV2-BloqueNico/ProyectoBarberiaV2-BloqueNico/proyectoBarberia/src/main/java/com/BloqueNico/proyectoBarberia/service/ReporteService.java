package com.BloqueNico.proyectoBarberia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.ReporteDTO;
import com.BloqueNico.proyectoBarberia.model.Reporte;
import com.BloqueNico.proyectoBarberia.repository.ReporteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReporteService {

    @Autowired 
    private ReporteRepository reporteRepository;

    @Autowired 
    private ReporteValidaciones validaciones;

    public List<ReporteDTO> traerTodosLosReportes() {
        log.info("Iniciando consulta de todos los reportes en la base de datos");
        return reporteRepository.findAll().stream()
                .map(validaciones::convertirADTO)
                .toList();
    }

    public ReporteDTO traerReportePorId(Long id) {
        log.info("Buscando reporte con ID: {}", id);
        Reporte reporte = buscarEntidadPorId(id);
        return validaciones.convertirADTO(reporte);
    }

    public ReporteDTO guardarNuevoReporte(ReporteDTO datos) {
        log.info("Generando nuevo reporte de tipo: {}", datos.getTipoDeReporte());
        Reporte nuevoReporte = new Reporte();
        nuevoReporte.setTipoDeReporte(datos.getTipoDeReporte());
        nuevoReporte.setTotalDeIngresosCalculado(datos.getTotalDeIngresosCalculado());
        nuevoReporte.setFechaDelReporte(datos.getFechaDelReporte());
        nuevoReporte.setResumenDelReporte(datos.getResumenDelReporte());
        if (!validaciones.validarReporte(nuevoReporte)) {
            throw new RuntimeException("Los datos del reporte son inválidos o contienen una fecha futura");
        }
        return validaciones.convertirADTO(reporteRepository.save(nuevoReporte));
    }

    public ReporteDTO actualizarReporte(Long id, ReporteDTO datosNuevos) {
        log.info("Actualizando reporte con ID: {}", id);
        Reporte reporteExistente = buscarEntidadPorId(id);
        reporteExistente.setTipoDeReporte(datosNuevos.getTipoDeReporte());
        reporteExistente.setTotalDeIngresosCalculado(datosNuevos.getTotalDeIngresosCalculado());
        reporteExistente.setFechaDelReporte(datosNuevos.getFechaDelReporte());
        reporteExistente.setResumenDelReporte(datosNuevos.getResumenDelReporte());
        if (!validaciones.validarReporte(reporteExistente)) {
            throw new RuntimeException("La actualización del reporte infringe las reglas de negocio");
        }
        
        return validaciones.convertirADTO(reporteRepository.save(reporteExistente));
    }

    public String eliminarReporte(Long id) {
        try {
            Reporte reporte = buscarEntidadPorId(id);
            reporteRepository.delete(reporte);
            return "El reporte de tipo '" + reporte.getTipoDeReporte() + "' ha sido eliminado";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    /**
     * Método auxiliar privado para resolver la búsqueda de entidades puras 
     * evitando conflictos de casteo con los DTOs públicos
     */
    private Reporte buscarEntidadPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: No se encontró el reporte con ID: " + id));
    }
}