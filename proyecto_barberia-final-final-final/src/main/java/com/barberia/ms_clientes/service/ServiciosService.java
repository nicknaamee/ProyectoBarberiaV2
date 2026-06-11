package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ServiciosDTO;
import com.barberia.ms_clientes.model.Servicios;
import com.barberia.ms_clientes.repository.ServiciosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiciosService {

    @Autowired
    private ServiciosRepository repo;

    public List<Servicios> traerTodos() {
        log.info("buscando todos los servicios");
        return repo.findAll();
    }

    public Servicios traerPorId(Long id) {
        log.info("buscando servicio con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el servicio con id: " + id));
    }

    public Servicios guardar(ServiciosDTO datos) {
        log.info("guardando nuevo servicio");
        Servicios nuevo = new Servicios();
        nuevo.setIdBarbero(datos.getIdBarbero());
        nuevo.setIdServicio(datos.getIdServicio());
        nuevo.setDisponible(datos.getDisponible());
        return repo.save(nuevo);
    }

    public Servicios actualizar(Long id, ServiciosDTO datos) {
        log.info("actualizando servicio con id: {}", id);
        Servicios servicio = traerPorId(id);
        servicio.setIdBarbero(datos.getIdBarbero());
        servicio.setIdServicio(datos.getIdServicio());
        servicio.setDisponible(datos.getDisponible());
        return repo.save(servicio);
    }

    public void eliminar(Long id) {
        log.info("eliminando servicio con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
