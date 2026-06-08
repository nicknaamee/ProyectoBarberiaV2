package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ResenasDTO;
import com.barberia.ms_clientes.model.Resenas;
import com.barberia.ms_clientes.repository.ResenasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResenasService {

    @Autowired
    private ResenasRepository repo;

    // trae todas las resenas
    public List<Resenas> traerTodas() {
        log.info("buscando todas las resenas");
        return repo.findAll();
    }

    // busca una resena por su id
    public Resenas traerPorId(Long id) {
        log.info("buscando resena con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la resena con id: " + id));
    }

    // guarda una nueva resena
    public Resenas guardar(ResenasDTO datos) {
        log.info("guardando nueva resena");
        Resenas nueva = new Resenas();
        nueva.setIdBarbero(datos.getIdBarbero());
        nueva.setTotalResenas(datos.getTotalResenas());
        nueva.setPromedioEstrellas(datos.getPromedioEstrellas());
        return repo.save(nueva);
    }

    // actualiza una resena existente
    public Resenas actualizar(Long id, ResenasDTO datos) {
        log.info("actualizando resena con id: {}", id);
        Resenas resena = traerPorId(id);
        resena.setIdBarbero(datos.getIdBarbero());
        resena.setTotalResenas(datos.getTotalResenas());
        resena.setPromedioEstrellas(datos.getPromedioEstrellas());
        return repo.save(resena);
    }

    // elimina una resena
    public void eliminar(Long id) {
        log.info("eliminando resena con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
