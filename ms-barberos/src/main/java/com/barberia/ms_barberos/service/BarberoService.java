package com.barberia.ms_barberos.service;

import com.barberia.ms_barberos.dto.BarberoDTO;
import com.barberia.ms_barberos.model.Barbero;
import com.barberia.ms_barberos.repository.BarberoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BarberoService {

    @Autowired
    private BarberoRepository repo;

    public List<Barbero> traerTodos() {
        log.info("buscando todos los barberos");
        return repo.findAll();
    }

    public Barbero traerPorId(Long id) {
        log.info("buscando barbero con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el barbero con id: " + id));
    }

    public Barbero guardar(BarberoDTO datos) {
        log.info("guardando nuevo barbero: {}", datos.getNombreDelBarbero());
        Barbero nuevo = new Barbero();
        nuevo.setNombreDelBarbero(datos.getNombreDelBarbero());
        nuevo.setEspecialidadDelBarbero(datos.getEspecialidadDelBarbero());
        nuevo.setEstaActivo(datos.getEstaActivo());
        return repo.save(nuevo);
    }

    public Barbero actualizar(Long id, BarberoDTO datos) {
        log.info("actualizando barbero con id: {}", id);
        Barbero barbero = traerPorId(id);
        barbero.setNombreDelBarbero(datos.getNombreDelBarbero());
        barbero.setEspecialidadDelBarbero(datos.getEspecialidadDelBarbero());
        barbero.setEstaActivo(datos.getEstaActivo());
        return repo.save(barbero);
    }

    public void eliminar(Long id) {
        log.info("eliminando barbero con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
