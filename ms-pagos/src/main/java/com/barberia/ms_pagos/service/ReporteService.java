package com.barberia.ms_pagos.service;

import com.barberia.ms_pagos.dto.ReporteDTO;
import com.barberia.ms_pagos.model.Reporte;
import com.barberia.ms_pagos.repository.ReporteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ReporteService {

    @Autowired
    private ReporteRepository repo;

    public List<Reporte> traerTodos() {
        log.info("buscando todos los reportes");
        return repo.findAll();
    }

    public Reporte traerPorId(Long id) {
        log.info("buscando reporte con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el reporte con id: " + id));
    }

    public Reporte guardar(ReporteDTO datos) {
        log.info("guardando nuevo reporte");
        Reporte nuevo = new Reporte();
        nuevo.setTipoDeReporte(datos.getTipoDeReporte());
        nuevo.setTotalDeIngresosCalculado(datos.getTotalDeIngresosCalculado());
        nuevo.setFechaDelReporte(LocalDate.now());
        nuevo.setResumenDelReporte(datos.getResumenDelReporte());
        return repo.save(nuevo);
    }

    public Reporte actualizar(Long id, ReporteDTO datos) {
        log.info("actualizando reporte con id: {}", id);
        Reporte reporte = traerPorId(id);
        reporte.setTipoDeReporte(datos.getTipoDeReporte());
        reporte.setTotalDeIngresosCalculado(datos.getTotalDeIngresosCalculado());
        reporte.setFechaDelReporte(datos.getFechaDelReporte());
        reporte.setResumenDelReporte(datos.getResumenDelReporte());
        return repo.save(reporte);
    }

    public void eliminar(Long id) {
        log.info("eliminando reporte con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
