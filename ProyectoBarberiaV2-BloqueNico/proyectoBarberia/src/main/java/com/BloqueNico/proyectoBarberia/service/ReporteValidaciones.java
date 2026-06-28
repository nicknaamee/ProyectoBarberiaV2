package com.BloqueNico.proyectoBarberia.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.model.Reporte;

@Service
public class ReporteValidaciones {

    public Boolean validarReporte(Reporte reporte) {
        if (reporte.getTipoDeReporte() == null || reporte.getTipoDeReporte().trim().isEmpty()) {
            return false;
        }
        if (reporte.getTotalDeIngresosCalculado() == null || reporte.getFechaDelReporte() == null) {
            return false;
        }
        if (reporte.getResumenDelReporte() == null || reporte.getResumenDelReporte().trim().isEmpty()) {
            return false;
        }
        if (reporte.getTipoDeReporte().length() > 50) {
            return false;
        }
        if (reporte.getResumenDelReporte().length() < 10 || reporte.getResumenDelReporte().length() > 500) {
            return false;
        }
        if (reporte.getTotalDeIngresosCalculado() < 0) {
            return false;
        }
        if (reporte.getFechaDelReporte().isAfter(LocalDate.now())) {
            return false;
        }

        return true;
    }
}