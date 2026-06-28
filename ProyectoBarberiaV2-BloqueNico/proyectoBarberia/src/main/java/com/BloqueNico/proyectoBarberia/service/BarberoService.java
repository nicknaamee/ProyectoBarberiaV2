package com.BloqueNico.proyectoBarberia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.BarberoDTO;
import com.BloqueNico.proyectoBarberia.model.Barbero;
import com.BloqueNico.proyectoBarberia.repository.BarberoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;

    public List<BarberoDTO> obtenerTodos(){
        log.info("Consultando todos los barberos registrados en la BD");
        return barberoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public BarberoDTO buscarPorId(Long idBarbero) {
        log.info("Buscando barbero con ID: {}", idBarbero);
        Barbero barbero = barberoRepository.findById(idBarbero)
            .orElseThrow(() -> new RuntimeException("No se encontró el barbero con ID: " + idBarbero));
        return convertirADTO(barbero);
    }

    public String eliminar(Long idBarbero){
        log.info("Intentando eliminar barbero con ID: {}", idBarbero);
        Barbero barbero = barberoRepository.findById(idBarbero)
                    .orElseThrow(() -> new RuntimeException("No existe Barbero con ese ID"));
        barberoRepository.delete(barbero);
        log.info("Barbero '{}' eliminado exitosamente", barbero.getNombreBarbero());
        return "El barbero '" + barbero.getNombreBarbero() + "' ha sido eliminado";
    }

    public Barbero guardarBarbero(Barbero barbero) {
        log.info("Guardando nuevo barbero: {}", barbero.getNombreBarbero());
        return barberoRepository.save(barbero);
    }

    public BarberoDTO guardar(Barbero barbero) {
        log.info("Registrando nuevo barbero (v2): {}", barbero.getNombreBarbero());
        Barbero guardado = barberoRepository.save(barbero);
        return convertirADTO(guardado);
    }

    public Barbero actualizarBarberos(Long idBarbero, Barbero barbero){
        log.info("Actualizando barbero con ID: {}", idBarbero);
        Barbero barber = barberoRepository.findById(idBarbero)
            .orElseThrow(() -> new RuntimeException("No existe Barbero con ese ID"));
        barber.setNombreBarbero(barbero.getNombreBarbero());
        barber.setEspecialidadBarbero(barbero.getEspecialidadBarbero());
        barber.setTelefonoBarbero(barbero.getTelefonoBarbero());
        return barberoRepository.save(barber);
    }

    public BarberoDTO actualizar(Long idBarbero, Barbero barbero) {
        log.info("Actualizando barbero (v2) con ID: {}", idBarbero);
        Barbero barber = barberoRepository.findById(idBarbero)
            .orElseThrow(() -> new RuntimeException("No existe Barbero con ese ID"));
        barber.setNombreBarbero(barbero.getNombreBarbero());
        barber.setEspecialidadBarbero(barbero.getEspecialidadBarbero());
        barber.setTelefonoBarbero(barbero.getTelefonoBarbero());
        if (barbero.getEstado() != null) {
            barber.setEstado(barbero.getEstado());
        }
        return convertirADTO(barberoRepository.save(barber));
    }

    public List<BarberoDTO> buscarPorEspecialidadBarbero(String especialidadBarbero){
        log.info("Buscando barberos con especialidad: {}", especialidadBarbero);
        return barberoRepository.findByEspecialidadBarbero(especialidadBarbero).stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    public List<BarberoDTO> buscarPorEspecialidad(String especialidad) {
        return buscarPorEspecialidadBarbero(especialidad);
    }

    public List<BarberoDTO> buscarPorEstado(boolean estado){
        log.info("Buscando barberos con estado: {}", estado);
        return barberoRepository.findByEstado(estado).stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    public List<BarberoDTO> obtenerBarberosActivos() {
        log.info("Consultando barberos activos");
        return buscarPorEstado(true);
    }

    public BarberoDTO convertirADTO(Barbero barbero){
        BarberoDTO dto = new BarberoDTO();
        dto.setIdBarbero(barbero.getIdBarbero());
        dto.setNombreBarbero(barbero.getNombreBarbero());
        dto.setEspecialidadBarbero(barbero.getEspecialidadBarbero());
        dto.setTelefonoBarbero(barbero.getTelefonoBarbero());
        return dto;
    }
}