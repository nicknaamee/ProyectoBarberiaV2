package com.BloqueNico.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.BloqueNico.dto.ReporteDTO;
import com.BloqueNico.model.Reporte;

@Service
public class ReporteValidaciones {


    @Autowired
    private WebClient.Builder webClientBuilder; 

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

    public ReporteDTO convertirADTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setIdDelReporte(reporte.getIdDelReporte());
        dto.setTipoDeReporte(reporte.getTipoDeReporte());
        dto.setTotalDeIngresosCalculado(reporte.getTotalDeIngresosCalculado());
        dto.setFechaDelReporte(reporte.getFechaDelReporte());
        dto.setResumenDelReporte(reporte.getResumenDelReporte());
        return dto;
    }
}