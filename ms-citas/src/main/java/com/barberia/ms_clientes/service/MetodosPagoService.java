package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.MetodosPagoDTO;
import com.barberia.ms_clientes.model.MetodosPago;
import com.barberia.ms_clientes.repository.MetodosPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MetodosPagoService {

    @Autowired
    private MetodosPagoRepository repo;

    public List<MetodosPago> traerTodos() {
        log.info("buscando todos los metodos por sucursal");
        return repo.findAll();
    }

    public MetodosPago traerPorId(Long id) {
        log.info("buscando metodo por sucursal con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el registro con id: " + id));
    }

    public MetodosPago guardar(MetodosPagoDTO datos) {
        log.info("guardando nuevo metodo por sucursal");
        MetodosPago nuevo = new MetodosPago();
        nuevo.setIdSucursal(datos.getIdSucursal());
        nuevo.setIdMetodoPago(datos.getIdMetodoPago());
        return repo.save(nuevo);
    }

    public MetodosPago actualizar(Long id, MetodosPagoDTO datos) {
        log.info("actualizando metodo por sucursal con id: {}", id);
        MetodosPago metodo = traerPorId(id);
        metodo.setIdSucursal(datos.getIdSucursal());
        metodo.setIdMetodoPago(datos.getIdMetodoPago());
        return repo.save(metodo);
    }

    public void eliminar(Long id) {
        log.info("eliminando metodo por sucursal con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
