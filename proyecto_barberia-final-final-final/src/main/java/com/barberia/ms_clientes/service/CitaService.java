package com.barberia.ms_clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.model.Barbero;
import com.barberia.ms_clientes.dto.CitaDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.repository.CitaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public CitaDTO agendarCita(Cita nuevaCita) {
        nuevaCita.setEstadoCita("Pendiente");
        Cita guardada = citaRepository.save(nuevaCita);
        return convertirADTO(guardada);
}

    public List<CitaDTO> obtenerTodos(){
        return citaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<CitaDTO> buscarPorBarbero(Long idBarbero) {
        Barbero barbero = new Barbero();
        barbero.setIdBarbero(idBarbero);

        return citaRepository.findByBarbero(barbero).stream()
                .map(this::convertirADTO)
                .toList();

    }

    public String eliminar(Long idCita){
        try{
            Cita Cita = citaRepository.findById(idCita)
                        .orElseThrow(() -> new RuntimeException("No existe Cita con ese ID"));
            citaRepository.delete(Cita);
            return "La Cita '" +Cita.getIdCita()+ "'ha sido eliminada";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public CitaDTO cambiarEstadoCita(Long idCita, String nuevoEstado) {
    Cita cita = citaRepository.findById(idCita)
            .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    cita.setEstadoCita(nuevoEstado);
    
    return convertirADTO(citaRepository.save(cita));

    }

    public Cita guardarCita(Cita Cita) {
        return citaRepository.save(Cita);
    }

    public Cita actualizarCitas(Long idCita, Cita datosNuevos){
        Cita citaExistente = citaRepository.findById(idCita).orElseThrow(()-> new RuntimeException("No existe Cita con ese ID"));

        if (datosNuevos.getHoraInicio() != null){
            citaExistente.setHoraInicio(datosNuevos.getHoraInicio());
        }

        if (datosNuevos.getEstadoCita() != null){
            citaExistente.setEstadoCita(datosNuevos.getEstadoCita());
        }

        if (datosNuevos.getFechaCita() != null){
            citaExistente.setFechaCita(datosNuevos.getFechaCita());
        }
        
        return citaRepository.save(citaExistente);
    }

    public List<CitaDTO> buscarPorEstadoCita(String estadoCita){
        return citaRepository.findByEstadoCita(estadoCita).stream()
                    .map(this::convertirADTO)
                    .toList();
    }
    

    private CitaDTO convertirADTO(Cita cita){
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(cita.getIdCita());
        dto.setFechaCita(cita.getFechaCita());
        dto.setHoraInicio(cita.getHoraInicio());
        dto.setEstadoCita(cita.getEstadoCita());

        return dto;
    }

    
}