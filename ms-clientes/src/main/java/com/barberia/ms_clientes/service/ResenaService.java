package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ResenaDTO;
import com.barberia.ms_clientes.model.Resena;
import com.barberia.ms_clientes.repository.ResenaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ResenaService {

    @Autowired
    private ResenaRepository repo;

    public List<Resena> traerTodas() {
        log.info("buscando todas las resenas");
        return repo.findAll();
    }

    public Resena traerPorId(Long id) {
        log.info("buscando resena con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la resena con id: " + id));
    }

    public Resena guardar(ResenaDTO datos) {
        log.info("guardando nueva resena");
        Resena nueva = new Resena();
        nueva.setIdDelClienteQueOpina(datos.getIdDelClienteQueOpina());
        nueva.setIdDelBarberoEvaluado(datos.getIdDelBarberoEvaluado());
        nueva.setCalificacionDeEstrellas(datos.getCalificacionDeEstrellas());
        nueva.setComentarioDelCliente(datos.getComentarioDelCliente());
        nueva.setFechaDeLaResena(LocalDate.now());
        return repo.save(nueva);
    }

    public Resena actualizar(Long id, ResenaDTO datos) {
        log.info("actualizando resena con id: {}", id);
        Resena resena = traerPorId(id);
        resena.setIdDelClienteQueOpina(datos.getIdDelClienteQueOpina());
        resena.setIdDelBarberoEvaluado(datos.getIdDelBarberoEvaluado());
        resena.setCalificacionDeEstrellas(datos.getCalificacionDeEstrellas());
        resena.setComentarioDelCliente(datos.getComentarioDelCliente());
        return repo.save(resena);
    }

    public void eliminar(Long id) {
        log.info("eliminando resena con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
