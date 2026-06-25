package com.BloqueNico.proyectoBarberia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.ServicioDTO;
import com.BloqueNico.proyectoBarberia.model.Servicio;
import com.BloqueNico.proyectoBarberia.repository.ServicioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServicioService {

    @Autowired 
    private ServicioRepository servicioRepository;

    @Autowired 
    private ServicioValidaciones validaciones;

    public List<ServicioDTO> traerTodosLosServicios() {
        log.info("Consultando la lista completa de servicios en la base de datos"); 
        return servicioRepository.findAll().stream()
                .map(validaciones::convertirADTO)
                .toList();
    }

    public ServicioDTO traerServicioPorId(Long idDelServicio) {
        log.info("Buscando servicio con ID: {}", idDelServicio); 
        Servicio servicio = buscarEntidadPorId(idDelServicio);
        return validaciones.convertirADTO(servicio);
    }

    public ServicioDTO guardarNuevoServicio(ServicioDTO datos) {
        log.info("Registrando nuevo servicio: {}", datos.getNombreDelServicio()); 
        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setNombreDelServicio(datos.getNombreDelServicio());
        nuevoServicio.setPrecioDelServicio(datos.getPrecioDelServicio());
        nuevoServicio.setDuracionEnMinutos(datos.getDuracionEnMinutos());
        if (!validaciones.validarServicio(nuevoServicio)) { 
            throw new RuntimeException("Los datos del servicio son inválidos (Verifique precio o duración)");
        }
        
        return validaciones.convertirADTO(servicioRepository.save(nuevoServicio)); 
    }

    public ServicioDTO actualizarServicio(Long idDelServicio, ServicioDTO datosNuevos) {
        log.info("Iniciando actualización del servicio con ID: {}", idDelServicio);
        Servicio servicioExistente = buscarEntidadPorId(idDelServicio);
        
        servicioExistente.setNombreDelServicio(datosNuevos.getNombreDelServicio());
        servicioExistente.setPrecioDelServicio(datosNuevos.getPrecioDelServicio());
        servicioExistente.setDuracionEnMinutos(datosNuevos.getDuracionEnMinutos());
        if (!validaciones.validarServicio(servicioExistente)) {
            throw new RuntimeException("La actualización del servicio contiene valores no permitidos");
        }
        
        return validaciones.convertirADTO(servicioRepository.save(servicioExistente));
    }

    public void eliminarServicio(Long idDelServicio) {
        log.info("Intentando eliminar servicio con ID: {}", idDelServicio);
        if (!servicioRepository.existsById(idDelServicio)) {
            throw new RuntimeException("Error: No se encontró el servicio con el ID: " + idDelServicio);
        }
        servicioRepository.deleteById(idDelServicio);
        log.info("Servicio con ID {} eliminado exitosamente", idDelServicio); 
    }

    private Servicio buscarEntidadPorId(Long idDelServicio) {
        return servicioRepository.findById(idDelServicio)
            .orElseThrow(() -> new RuntimeException("Error: No se encontró el servicio con el ID: " + idDelServicio));
    }
}