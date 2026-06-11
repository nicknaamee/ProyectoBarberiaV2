package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.ResenasDTO;
import com.barberia.ms_clientes.model.Barbero;
import com.barberia.ms_clientes.model.Resenas;
import com.barberia.ms_clientes.repository.BarberoRepository;
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

    @Autowired
    private BarberoRepository barberoRepo;

    public List<Resenas> traerTodas() {
        log.info("buscando todas las resenas");
        return repo.findAll();
    }

    public Resenas traerPorId(Long id) {
        log.info("buscando resena con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la resena con id: " + id));
    }

    public Resenas guardar(ResenasDTO datos) {
        log.info("guardando nueva resena");


        Barbero barberoExistente = barberoRepo.findById(datos.getIdBarbero())
            .orElseThrow(() -> new RuntimeException("El barbero no existe"));

        Resenas nueva = new Resenas();

        nueva.setBarbero(barberoExistente); 
        nueva.setTotalResenas(datos.getTotalResenas());
        nueva.setPromedioEstrellas(datos.getPromedioEstrellas());
        
        return repo.save(nueva);
    }

    public Resenas actualizar(Long id, ResenasDTO datos) {
        log.info("actualizando resena con id: {}", id);
        Resenas resena = traerPorId(id);

        Barbero barberoExistente = barberoRepo.findById(datos.getIdBarbero())
            .orElseThrow(() -> new RuntimeException("El barbero no existe"));

        resena.setBarbero(barberoExistente);
        resena.setTotalResenas(datos.getTotalResenas());
        resena.setPromedioEstrellas(datos.getPromedioEstrellas());
        
        return repo.save(resena);
    }

    public void eliminar(Long id) {
        log.info("eliminando resena con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
