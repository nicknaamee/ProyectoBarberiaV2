package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.FacturaDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.model.Factura;
import com.barberia.ms_clientes.repository.CitaRepository;
import com.barberia.ms_clientes.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.barberia.ms_clientes.dto.ServicioExternoDTO;

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