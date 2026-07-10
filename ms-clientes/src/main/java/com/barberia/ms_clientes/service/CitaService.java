package com.barberia.ms_clientes.service;

import com.barberia.ms_clientes.dto.CitaDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.repository.CitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CitaService {

    @Autowired
    private CitaRepository repo;

    public List<Cita> traerTodas() {
        log.info("buscando todas las citas");
        return repo.findAll();
    }

    public Cita traerPorId(Long id) {
        log.info("buscando cita con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la cita con id: " + id));
    }

    public Cita guardar(CitaDTO datos) {
        log.info("guardando nueva cita");
        Cita nueva = new Cita();
        nueva.setIdDelClienteQueAgenda(datos.getIdDelClienteQueAgenda());
        nueva.setIdDelBarberoAsignado(datos.getIdDelBarberoAsignado());
        nueva.setIdDelServicioSolicitado(datos.getIdDelServicioSolicitado());
        nueva.setFechaDeLaCita(datos.getFechaDeLaCita());
        nueva.setHoraDeInicio(datos.getHoraDeInicio());
        nueva.setEstadoDeLaCita(datos.getEstadoDeLaCita());
        return repo.save(nueva);
    }

    public Cita actualizar(Long id, CitaDTO datos) {
        log.info("actualizando cita con id: {}", id);
        Cita cita = traerPorId(id);
        cita.setIdDelClienteQueAgenda(datos.getIdDelClienteQueAgenda());
        cita.setIdDelBarberoAsignado(datos.getIdDelBarberoAsignado());
        cita.setIdDelServicioSolicitado(datos.getIdDelServicioSolicitado());
        cita.setFechaDeLaCita(datos.getFechaDeLaCita());
        cita.setHoraDeInicio(datos.getHoraDeInicio());
        cita.setEstadoDeLaCita(datos.getEstadoDeLaCita());
        return repo.save(cita);
    }

    public void eliminar(Long id) {
        log.info("eliminando cita con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
