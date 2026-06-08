package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.CitasDTO;
import com.barberia.ms_clientes.model.Citas;
import com.barberia.ms_clientes.repository.CitasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CitasService {

    @Autowired
    private CitasRepository repo;

    // trae todas las citas
    public List<Citas> traerTodas() {
        log.info("buscando todas las citas");
        return repo.findAll();
    }

    // busca una cita por su id
    public Citas traerPorId(Long id) {
        log.info("buscando cita con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la cita con id: " + id));
    }

    // guarda una nueva cita
    public Citas guardar(CitasDTO datos) {
        log.info("guardando nueva cita");
        Citas nueva = new Citas();
        nueva.setIdCliente(datos.getIdCliente());
        nueva.setTotalCitas(datos.getTotalCitas());
        return repo.save(nueva);
    }

    // actualiza una cita existente
    public Citas actualizar(Long id, CitasDTO datos) {
        log.info("actualizando cita con id: {}", id);
        Citas cita = traerPorId(id);
        cita.setIdCliente(datos.getIdCliente());
        cita.setTotalCitas(datos.getTotalCitas());
        return repo.save(cita);
    }

    // elimina una cita
    public void eliminar(Long id) {
        log.info("eliminando cita con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
