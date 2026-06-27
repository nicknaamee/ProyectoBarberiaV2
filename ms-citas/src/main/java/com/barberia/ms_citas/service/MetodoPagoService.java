package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.MetodoPagoDTO;
import com.barberia.ms_clientes.model.MetodoPago;
import com.barberia.ms_clientes.repository.MetodoPagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repo;

    @Autowired
    private MetodoPagoValidaciones validaciones;

    public List<MetodoPagoDTO> traerTodos() {
        log.info("buscando todos los metodos de pago");
        List<MetodoPagoDTO> listaDTOs = new ArrayList<>();
        List<MetodoPago> metodos = repo.findAll();
        for (MetodoPago m : metodos) {
            listaDTOs.add(validaciones.convertirADTO(m));
        }
        return listaDTOs;
    }

    public MetodoPagoDTO traerPorId(Long id) {
        log.info("buscando metodo de pago con id: {}", id);
        MetodoPago metodo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el metodo de pago con id: " + id));
        return validaciones.convertirADTO(metodo);
    }

    public MetodoPagoDTO guardar(MetodoPagoDTO datos) {
        log.info("guardando nuevo metodo de pago: {}", datos.getNombre());
        if (!validaciones.validarNullVacio(datos)) {
            throw new RuntimeException("Faltan datos obligatorios");
        }
        MetodoPago nuevo = new MetodoPago();
        nuevo.setNombre(datos.getNombre());
        nuevo.setActivo(datos.getActivo());
        return validaciones.convertirADTO(repo.save(nuevo));
    }

    public MetodoPagoDTO actualizar(Long id, MetodoPagoDTO datos) {
        log.info("actualizando metodo de pago con id: {}", id);
        MetodoPago metodo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el metodo de pago con id: " + id));
        if (!validaciones.validarNullVacio(datos)) {
            throw new RuntimeException("Faltan datos obligatorios");
        }
        metodo.setNombre(datos.getNombre());
        metodo.setActivo(datos.getActivo());
        return validaciones.convertirADTO(repo.save(metodo));
    }

    public void eliminar(Long id) {
        log.info("eliminando metodo de pago con id: {}", id);
        MetodoPago metodo = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el metodo de pago con id: " + id));
        repo.delete(metodo);
    }
}
