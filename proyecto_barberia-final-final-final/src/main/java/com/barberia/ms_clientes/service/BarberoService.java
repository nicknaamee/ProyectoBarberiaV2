package com.barberia.ms_clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberia.ms_clientes.dto.BarberoDTO;
import com.barberia.ms_clientes.model.Barbero;
import com.barberia.ms_clientes.repository.BarberoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;

    public List<BarberoDTO> obtenerTodos(){
        return barberoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public BarberoDTO buscarPorId(Long idBarbero) {
        Barbero barbero = barberoRepository.findById(idBarbero)
            .orElseThrow(() ->new RuntimeException("Barbero no encontrado"));
        return convertirADTO(barbero);

    }

    public String eliminar(Long idBarbero){
        try{
            Barbero barbero = barberoRepository.findById(idBarbero)
                        .orElseThrow(() -> new RuntimeException("No existe Barbero con ese ID"));
            barberoRepository.delete(barbero);
            return "El barbero '" +barbero.getNombreBarbero()+ "'ha sido eliminado";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Barbero guardarBarbero(Barbero barbero) {
        return barberoRepository.save(barbero);
    }

    public Barbero actualizarBarberos(Long idBarbero, Barbero barbero){
        Barbero barber = barberoRepository.findById(idBarbero).orElseThrow(()-> new RuntimeException("No existe Barbero con ese ID"));

        if (barbero.getNombreBarbero() != null){
            barber.setNombreBarbero(barbero.getNombreBarbero());
        }

        if (barbero.getEspecialidadBarbero() != null){
            barber.setEspecialidadBarbero(barbero.getEspecialidadBarbero());
        }

        if (barbero.getTelefonoBarbero() != null){
            barber.setTelefonoBarbero(barbero.getTelefonoBarbero());
        }

        return barberoRepository.save(barbero);
        
    }

    public List<BarberoDTO> buscarPorEspecialidadBarbero(String especialidadBarbero){
        return barberoRepository.findByEspecialidadBarbero(especialidadBarbero).stream()
                    .map(this::convertirADTO)
                    .toList();
    }
    
    public List<BarberoDTO> buscarPorEstado(boolean estado){
        return barberoRepository.findByEstado(estado).stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    private BarberoDTO convertirADTO(Barbero barbero){
        BarberoDTO dto = new BarberoDTO();
        dto.setTelefonoBarbero(barbero.getTelefonoBarbero());
        dto.setNombreBarbero(barbero.getNombreBarbero());
        dto.setEspecialidadBarbero(barbero.getEspecialidadBarbero());
        dto.setTelefonoBarbero(barbero.getTelefonoBarbero());
        return dto;
    }

    
}