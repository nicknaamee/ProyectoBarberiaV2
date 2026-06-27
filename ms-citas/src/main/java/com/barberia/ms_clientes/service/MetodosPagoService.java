package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.MetodosPagoDTO;
import com.barberia.ms_clientes.model.MetodosPago;
import com.barberia.ms_clientes.repository.MetodosPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MetodosPagoService {

    @Autowired
    private MetodosPagoRepository repo;

    @Autowired
    private MetodosPagoValidaciones validaciones;

    public List<MetodosPagoDTO> traerTodos() {
        log.info("buscando todos los metodos por sucursal");
        List<MetodosPagoDTO> listaDTOs = new ArrayList<>();
        List<MetodosPago> metodos = repo.findAll();
        for (MetodosPago m : metodos) {
            listaDTOs.add(validaciones.convertirADTO(m));
        }
        return listaDTOs;
    }

    public MetodosPagoDTO traerPorId(Long id) {
        log.info("buscando metodo por sucursal con id: {}", id);
        MetodosPago metodo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el registro con id: " + id));
        return validaciones.convertirADTO(metodo);
    }

    public MetodosPagoDTO guardar(MetodosPagoDTO datos) {
        log.info("guardando nuevo metodo por sucursal");
        if (!validaciones.validarNullVacio(datos)) {
            throw new RuntimeException("Faltan datos obligatorios");
        }
        MetodosPago nuevo = new MetodosPago();
        nuevo.setIdSucursal(datos.getIdSucursal());
        nuevo.setIdMetodoPago(datos.getIdMetodoPago());
        return validaciones.convertirADTO(repo.save(nuevo));
    }

    public MetodosPagoDTO actualizar(Long id, MetodosPagoDTO datos) {
        log.info("actualizando metodo por sucursal con id: {}", id);
        MetodosPago metodo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el registro con id: " + id));
        if (!validaciones.validarNullVacio(datos)) {
            throw new RuntimeException("Faltan datos obligatorios");
        }
        metodo.setIdSucursal(datos.getIdSucursal());
        metodo.setIdMetodoPago(datos.getIdMetodoPago());
        return validaciones.convertirADTO(repo.save(metodo));
    }

    public void eliminar(Long id) {
        log.info("eliminando metodo por sucursal con id: {}", id);
        MetodosPago metodo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el registro con id: " + id));
        repo.delete(metodo);
    }
}
