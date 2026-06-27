package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.CitaDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.repository.CitaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private CitaValidaciones citaValidaciones;

    public CitaDTO agendarCita(Cita nuevaCita) {
        if (citaValidaciones.validarNullVacio(nuevaCita)) {
            nuevaCita.setEstadoCita("Pendiente");
            Cita guardada = citaRepository.save(nuevaCita);
            return citaValidaciones.convertirADTO(guardada);
        }
        return null;
    }

    public List<CitaDTO> obtenerTodos() {
        List<CitaDTO> listaDTOs = new ArrayList<>();
        List<Cita> citasReales = citaRepository.findAll();
        for (Cita c : citasReales) {
            listaDTOs.add(citaValidaciones.convertirADTO(c));
        }
        return listaDTOs;
    }

    public List<CitaDTO> buscarPorBarbero(Long idBarbero) {
        List<CitaDTO> listaDTOs = new ArrayList<>();
        List<Cita> citasFiltradas = citaRepository.findByIdBarbero(idBarbero);
        for (Cita c : citasFiltradas) {
            listaDTOs.add(citaValidaciones.convertirADTO(c));
        }
        return listaDTOs;
    }

    public String eliminar(Long idCita) {
        try {
            Cita cita = citaRepository.findById(idCita)
                    .orElseThrow(() -> new RuntimeException("No existe Cita con ese ID"));
            citaRepository.delete(cita);
            return "La Cita '" + cita.getIdCita() + "' ha sido eliminada";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public CitaDTO cambiarEstadoCita(Long idCita, String nuevoEstado) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstadoCita(nuevoEstado);

        return citaValidaciones.convertirADTO(citaRepository.save(cita));
    }

    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita actualizarCitas(Long idCita, Cita datosNuevos) {
        Cita citaExistente = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("No existe Cita con ese ID"));

        if (datosNuevos.getHoraInicio() != null) {
            citaExistente.setHoraInicio(datosNuevos.getHoraInicio());
        }

        if (datosNuevos.getEstadoCita() != null) {
            citaExistente.setEstadoCita(datosNuevos.getEstadoCita());
        }

        if (datosNuevos.getFechaCita() != null) {
            citaExistente.setFechaCita(datosNuevos.getFechaCita());
        }

        return citaRepository.save(citaExistente);
    }

    public List<CitaDTO> buscarPorEstadoCita(String estadoCita) {
        List<CitaDTO> listaDTOs = new ArrayList<>();
        List<Cita> citasPorEstado = citaRepository.findByEstadoCita(estadoCita);
        for (Cita c : citasPorEstado) {
            listaDTOs.add(citaValidaciones.convertirADTO(c));
        }
        return listaDTOs;
    }
}