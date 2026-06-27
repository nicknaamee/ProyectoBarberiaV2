package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.MetodoPagoDTO;
import com.barberia.ms_clientes.model.MetodoPago;
import com.barberia.ms_clientes.repository.MetodoPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repo;

    public List<MetodoPago> traerTodos() {
        log.info("buscando todos los metodos de pago");
        return repo.findAll();
    }

    public MetodoPago traerPorId(Long id) {
        log.info("buscando metodo de pago con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el metodo de pago con id: " + id));
    }

    public MetodoPago guardar(MetodoPagoDTO datos) {
        log.info("guardando nuevo metodo de pago: {}", datos.getNombre());
        MetodoPago nuevo = new MetodoPago();
        nuevo.setNombre(datos.getNombre());
        nuevo.setActivo(datos.getActivo());
        return repo.save(nuevo);
    }

    public MetodoPago actualizar(Long id, MetodoPagoDTO datos) {
        log.info("actualizando metodo de pago con id: {}", id);
        MetodoPago metodo = traerPorId(id);
        metodo.setNombre(datos.getNombre());
        metodo.setActivo(datos.getActivo());
        return repo.save(metodo);
    }

    public void eliminar(Long id) {
        log.info("eliminando metodo de pago con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
