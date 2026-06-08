package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.FacturaDTO;
import com.barberia.ms_clientes.model.Factura;
import com.barberia.ms_clientes.repository.FacturaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FacturaService {

    @Autowired
    private FacturaRepository repo;

    public List<Factura> traerTodas() {
        log.info("buscando todas las facturas");
        return repo.findAll();
    }

    public Factura traerPorId(Long id) {
        log.info("buscando factura con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la factura con id: " + id));
    }

    public Factura guardar(FacturaDTO datos) {
        log.info("guardando nueva factura");
        Factura nueva = new Factura();
        nueva.setIdDeLaCitaFacturada(datos.getIdDeLaCitaFacturada());
        nueva.setMontoTotalCobrado(datos.getMontoTotalCobrado());
        nueva.setMetodoDePago(datos.getMetodoDePago());
        nueva.setFechaYHoraDeEmision(LocalDateTime.now());
        return repo.save(nueva);
    }

    public Factura actualizar(Long id, FacturaDTO datos) {
        log.info("actualizando factura con id: {}", id);
        Factura factura = traerPorId(id);
        factura.setIdDeLaCitaFacturada(datos.getIdDeLaCitaFacturada());
        factura.setMontoTotalCobrado(datos.getMontoTotalCobrado());
        factura.setMetodoDePago(datos.getMetodoDePago());
        factura.setFechaYHoraDeEmision(datos.getFechaYHoraDeEmision());
        return repo.save(factura);
    }

    public void eliminar(Long id) {
        log.info("eliminando factura con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
