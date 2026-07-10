package com.barberia.ms_barberos.service;

import com.barberia.ms_barberos.dto.ServicioDTO;
import com.barberia.ms_barberos.model.Servicio;
import com.barberia.ms_barberos.repository.ServicioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServicioService {

    @Autowired
    private ServicioRepository repo;

    public List<Servicio> traerTodos() {
        log.info("buscando todos los servicios");
        return repo.findAll();
    }

    public Servicio traerPorId(Long id) {
        log.info("buscando servicio con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el servicio con id: " + id));
    }

    public Servicio guardar(ServicioDTO datos) {
        log.info("guardando nuevo servicio: {}", datos.getNombreDelServicio());
        Servicio nuevo = new Servicio();
        nuevo.setNombreDelServicio(datos.getNombreDelServicio());
        nuevo.setPrecioDelServicio(datos.getPrecioDelServicio());
        nuevo.setDuracionEnMinutos(datos.getDuracionEnMinutos());
        return repo.save(nuevo);
    }

    public Servicio actualizar(Long id, ServicioDTO datos) {
        log.info("actualizando servicio con id: {}", id);
        Servicio servicio = traerPorId(id);
        servicio.setNombreDelServicio(datos.getNombreDelServicio());
        servicio.setPrecioDelServicio(datos.getPrecioDelServicio());
        servicio.setDuracionEnMinutos(datos.getDuracionEnMinutos());
        return repo.save(servicio);
    }

    public void eliminar(Long id) {
        log.info("eliminando servicio con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
