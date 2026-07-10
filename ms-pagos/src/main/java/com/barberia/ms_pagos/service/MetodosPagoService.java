package com.barberia.ms_pagos.service;

import com.barberia.ms_pagos.dto.MetodosPagoDTO;
import com.barberia.ms_pagos.model.MetodosPago;
import com.barberia.ms_pagos.repository.MetodosPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetodosPagoService {

    @Autowired
    private MetodosPagoRepository repo;

    // trae todos los metodos por sucursal
    public List<MetodosPago> traerTodos() {
        log.info("buscando todos los metodos por sucursal");
        return repo.findAll();
    }

    // busca un registro por su id
    public MetodosPago traerPorId(Long id) {
        log.info("buscando metodo por sucursal con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el registro con id: " + id));
    }

    // guarda un nuevo registro
    public MetodosPago guardar(MetodosPagoDTO datos) {
        log.info("guardando nuevo metodo por sucursal");
        MetodosPago nuevo = new MetodosPago();
        nuevo.setIdSucursal(datos.getIdSucursal());
        nuevo.setIdMetodoPago(datos.getIdMetodoPago());
        return repo.save(nuevo);
    }

    // actualiza un registro existente
    public MetodosPago actualizar(Long id, MetodosPagoDTO datos) {
        log.info("actualizando metodo por sucursal con id: {}", id);
        MetodosPago metodo = traerPorId(id);
        metodo.setIdSucursal(datos.getIdSucursal());
        metodo.setIdMetodoPago(datos.getIdMetodoPago());
        return repo.save(metodo);
    }

    // elimina un registro
    public void eliminar(Long id) {
        log.info("eliminando metodo por sucursal con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
