package com.barberia.ms_pagos.service;

import com.barberia.ms_pagos.dto.MetodoPagoDTO;
import com.barberia.ms_pagos.model.MetodoPago;
import com.barberia.ms_pagos.repository.MetodoPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repo;

    // trae todos los metodos de pago
    public List<MetodoPago> traerTodos() {
        log.info("buscando todos los metodos de pago");
        return repo.findAll();
    }

    // busca un metodo de pago por su id
    public MetodoPago traerPorId(Long id) {
        log.info("buscando metodo de pago con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el metodo de pago con id: " + id));
    }

    // guarda un nuevo metodo de pago
    public MetodoPago guardar(MetodoPagoDTO datos) {
        log.info("guardando nuevo metodo de pago: {}", datos.getNombre());
        MetodoPago nuevo = new MetodoPago();
        nuevo.setNombre(datos.getNombre());
        nuevo.setActivo(datos.getActivo());
        return repo.save(nuevo);
    }

    // actualiza un metodo de pago existente
    public MetodoPago actualizar(Long id, MetodoPagoDTO datos) {
        log.info("actualizando metodo de pago con id: {}", id);
        MetodoPago metodo = traerPorId(id);
        metodo.setNombre(datos.getNombre());
        metodo.setActivo(datos.getActivo());
        return repo.save(metodo);
    }

    // elimina un metodo de pago
    public void eliminar(Long id) {
        log.info("eliminando metodo de pago con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
