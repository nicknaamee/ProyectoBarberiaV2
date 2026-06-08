package com.barberia.ms_productos.service;

import com.barberia.ms_productos.dto.PromocionDTO;
import com.barberia.ms_productos.model.Promocion;
import com.barberia.ms_productos.repository.PromocionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PromocionService {

    @Autowired
    private PromocionRepository repo;

    public List<Promocion> traerTodos() {
        log.info("buscando todas las promociones");
        return repo.findAll();
    }

    public Promocion traerPorId(Long id) {
        log.info("buscando promocion con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la promocion con id: " + id));
    }

    public Promocion guardar(PromocionDTO datos) {
        log.info("guardando nueva promocion: {}", datos.getDescripcionDeLaPromocion());
        Promocion nuevo = new Promocion();
        nuevo.setDescripcionDeLaPromocion(datos.getDescripcionDeLaPromocion());
        nuevo.setPorcentajeDeDescuento(datos.getPorcentajeDeDescuento());
        nuevo.setFechaInicioDePromocion(datos.getFechaInicioDePromocion());
        nuevo.setFechaFinDePromocion(datos.getFechaFinDePromocion());
        return repo.save(nuevo);
    }

    public Promocion actualizar(Long id, PromocionDTO datos) {
        log.info("actualizando promocion con id: {}", id);
        Promocion promocion = traerPorId(id);
        promocion.setDescripcionDeLaPromocion(datos.getDescripcionDeLaPromocion());
        promocion.setPorcentajeDeDescuento(datos.getPorcentajeDeDescuento());
        promocion.setFechaInicioDePromocion(datos.getFechaInicioDePromocion());
        promocion.setFechaFinDePromocion(datos.getFechaFinDePromocion());
        return repo.save(promocion);
    }

    public void eliminar(Long id) {
        log.info("eliminando promocion con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
