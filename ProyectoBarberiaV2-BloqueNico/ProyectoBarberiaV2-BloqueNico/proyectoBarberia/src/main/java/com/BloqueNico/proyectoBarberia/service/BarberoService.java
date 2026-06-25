package com.BloqueNico.proyectoBarberia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.BarberoDTO;
import com.BloqueNico.proyectoBarberia.model.Barbero;
import com.BloqueNico.proyectoBarberia.repository.BarberoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BarberoService {

    @Autowired 
    private BarberoRepository barberoRepository;

    public List<BarberoDTO> obtenerTodos() {
        log.info("Consultando la lista completa de barberos");
        return barberoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public BarberoDTO buscarPorId(Long id) {
        log.info("Buscando barbero con ID: {}", id);
        Barbero barbero = buscarEntidadPorId(id);
        return convertirADTO(barbero);
    }

    public BarberoDTO guardarBarbero(BarberoDTO datos) {
        log.info("Registrando nuevo barbero: {}", datos.getNombreBarbero());
        
        Barbero nuevoBarbero = new Barbero();
        nuevoBarbero.setNombreBarbero(datos.getNombreBarbero());
        nuevoBarbero.setEspecialidadBarbero(datos.getEspecialidadBarbero());
        nuevoBarbero.setTelefonoBarbero(datos.getTelefonoBarbero());
        nuevoBarbero.setEstado(datos.getEstado() != null ? datos.getEstado() : true);
        
        return convertirADTO(barberoRepository.save(nuevoBarbero));
    }

    public BarberoDTO actualizarBarberos(Long id, BarberoDTO datosNuevos) {
        log.info("Actualizando datos del barbero con ID: {}", id);
        Barbero barberoExistente = buscarEntidadPorId(id);
        
        barberoExistente.setNombreBarbero(datosNuevos.getNombreBarbero());
        barberoExistente.setEspecialidadBarbero(datosNuevos.getEspecialidadBarbero());
        barberoExistente.setTelefonoBarbero(datosNuevos.getTelefonoBarbero());
        barberoExistente.setEstado(datosNuevos.getEstado());
        
        return convertirADTO(barberoRepository.save(barberoExistente));
    }

    public void eliminar(Long id) {
        log.info("Eliminando barbero con ID: {}", id);
        if (!barberoRepository.existsById(id)) {
            throw new RuntimeException("No se encontró el barbero con el ID: " + id);
        }
        barberoRepository.deleteById(id);
    }

    public List<BarberoDTO> buscarPorEspecialidadBarbero(String especialidad) {
        log.info("Filtrando barberos por especialidad: {}", especialidad);
        return barberoRepository.findByEspecialidadBarbero(especialidad).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<BarberoDTO> buscarPorEstado(boolean estado) {
        log.info("Filtrando barberos por estado: {}", estado);
        return barberoRepository.findAll().stream()
                .filter(b -> b.getEstado() == estado)
                .map(this::convertirADTO)
                .toList();
    }

    /**
     * Métodos auxiliares de conversión (Mapeo interno)
     */
    private BarberoDTO convertirADTO(Barbero barbero) {
        return BarberoDTO.builder()
                .idBarbero(barbero.getIdBarbero())
                .nombreBarbero(barbero.getNombreBarbero())
                .especialidadBarbero(barbero.getEspecialidadBarbero())
                .telefonoBarbero(barbero.getTelefonoBarbero())
                .estado(barbero.getEstado())
                .build();
    }

    private Barbero buscarEntidadPorId(Long id) {
        return barberoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el barbero con el ID: " + id));
    }

}