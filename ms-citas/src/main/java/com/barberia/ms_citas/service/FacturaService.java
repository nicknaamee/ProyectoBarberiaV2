package com.barberia.ms_citas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_citas.dto.FacturaDTO;
import com.barberia.ms_citas.dto.ServicioExternoDTO;
import com.barberia.ms_citas.model.Cita;
import com.barberia.ms_citas.model.Factura;
import com.barberia.ms_citas.repository.CitaRepository;
import com.barberia.ms_citas.repository.FacturaRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private FacturaValidaciones facturaValidaciones;

    public FacturaDTO generarFactura(Long idCita, String metodo) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        ServicioExternoDTO servicio = facturaValidaciones.obtenerServicio(cita.getIdServicio());
        Double costoFinal = servicio.getPrecioDelServicio();

        if (facturaRepository.findByCitaIdCita(idCita).isPresent()) {
            throw new RuntimeException("Esta cita ya fue facturada anteriormente");
        }

        Factura factura = new Factura();
        factura.setCita(cita);
        factura.setMontoTotal(costoFinal);
        factura.setMetodoDePago(metodo);
        factura.setFechaEmision(LocalDateTime.now());

        return facturaValidaciones.convertirADTO(facturaRepository.save(factura));
    }

    public FacturaDTO cambiarMetodoPago(Long id, String nuevoMetodo) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe factura con ese ID"));

        factura.setMetodoDePago(nuevoMetodo);
        return facturaValidaciones.convertirADTO(facturaRepository.save(factura));
    }

    public List<FacturaDTO> obtenerTodas() {
        List<FacturaDTO> listaDTOs = new ArrayList<>();
        List<Factura> facturas = facturaRepository.findAll();
        for (Factura f : facturas) {
            listaDTOs.add(facturaValidaciones.convertirADTO(f));
        }
        return listaDTOs;
    }

    public FacturaDTO obtenerPorId(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe factura con ese ID"));
        return facturaValidaciones.convertirADTO(factura);
    }
}