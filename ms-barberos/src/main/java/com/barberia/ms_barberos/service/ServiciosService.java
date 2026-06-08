package com.barberia.ms_barberos.service;

import com.barberia.ms_barberos.dto.ServiciosDTO;
import com.barberia.ms_barberos.model.Servicios;
import com.barberia.ms_barberos.repository.ServiciosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiciosService {

    @Autowired
    private ServiciosRepository repo;

    // trae todos los servicios
    public List<Servicios> traerTodos() {
        log.info("buscando todos los servicios");
        return repo.findAll();
    }

    // busca un servicio por su id
    public Servicios traerPorId(Long id) {
        log.info("buscando servicio con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el servicio con id: " + id));
    }

    // guarda un nuevo servicio
    public Servicios guardar(ServiciosDTO datos) {
        log.info("guardando nuevo servicio");
        Servicios nuevo = new Servicios();
        nuevo.setIdBarbero(datos.getIdBarbero());
        nuevo.setIdServicio(datos.getIdServicio());
        nuevo.setDisponible(datos.getDisponible());
        return repo.save(nuevo);
    }

    // actualiza un servicio existente
    public Servicios actualizar(Long id, ServiciosDTO datos) {
        log.info("actualizando servicio con id: {}", id);
        Servicios servicio = traerPorId(id);
        servicio.setIdBarbero(datos.getIdBarbero());
        servicio.setIdServicio(datos.getIdServicio());
        servicio.setDisponible(datos.getDisponible());
        return repo.save(servicio);
    }

    // elimina un servicio
    public void eliminar(Long id) {
        log.info("eliminando servicio con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
