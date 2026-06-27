package com.barberia.ms_clientes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.barberia.ms_clientes.dto.CitaDTO;
import com.barberia.ms_clientes.dto.BarberoExternoDTO;
import com.barberia.ms_clientes.model.Cita;
import com.barberia.ms_clientes.repository.CitaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public CitaDTO agendarCita(Cita nuevaCita) {
        nuevaCita.setEstadoCita("Pendiente");
        Cita guardada = citaRepository.save(nuevaCita);
        return convertirADTO(guardada);
    }

    public List<CitaDTO> obtenerTodos(){
        List<CitaDTO> listaDTOs = new ArrayList<>();
        List<Cita> citasReales = citaRepository.findAll();
        for (Cita c : citasReales) {
            listaDTOs.add(convertirADTO(c));
        }
        return listaDTOs;
    }

    public List<CitaDTO> buscarPorBarbero(Long idBarbero) {
        List<CitaDTO> listaDTOs = new ArrayList<>();
        List<Cita> citasFiltradas = citaRepository.findByIdBarbero(idBarbero);
        for (Cita c : citasFiltradas) {
            listaDTOs.add(convertirADTO(c));
        }
        return listaDTOs;
    }

    public String eliminar(Long idCita){
        try{
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
        
        return convertirADTO(citaRepository.save(cita));
    }

    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
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
        List<CitaDTO> listaDTOs = new ArrayList<>();
        List<Cita> citasPorEstado = citaRepository.findByEstadoCita(estadoCita);
        for (Cita c : citasPorEstado) {
            listaDTOs.add(convertirADTO(c));
        }
        return listaDTOs;
    }
    
    private CitaDTO convertirADTO(Cita cita){
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(cita.getIdCita());
        dto.setFechaCita(cita.getFechaCita());
        dto.setHoraInicio(cita.getHoraInicio());
        dto.setEstadoCita(cita.getEstadoCita());

        try {
            BarberoExternoDTO barberoDetectado = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/v1/barberos/" + cita.getIdBarbero())
                .retrieve()
                .bodyToMono(BarberoExternoDTO.class)
                .block();

            dto.setBarbero(barberoDetectado);
        } catch (Exception e) {
            BarberoExternoDTO errorDto = new BarberoExternoDTO();
            errorDto.setIdBarbero(cita.getIdBarbero());
            errorDto.setNombreBarbero("Desconectado del servicio de barberos (offline o no existe)");
            dto.setBarbero(errorDto);
        }

        return dto;
    }
}