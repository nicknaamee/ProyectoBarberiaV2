package com.BloqueNico.proyectoBarberia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BloqueNico.proyectoBarberia.dto.ResenaDTO;
import com.BloqueNico.proyectoBarberia.model.Barbero;
import com.BloqueNico.proyectoBarberia.model.Cliente;
import com.BloqueNico.proyectoBarberia.model.Resena;
import com.BloqueNico.proyectoBarberia.repository.BarberoRepository;
import com.BloqueNico.proyectoBarberia.repository.ClienteRepository;
import com.BloqueNico.proyectoBarberia.repository.ResenaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired 
    private ClienteRepository clienteRepository;

    @Autowired 
    private BarberoRepository barberoRepository;

    @Autowired
    private ResenaValidaciones validaciones;

    public List<ResenaDTO> traerTodasLasResenas() {
        log.info("Consultando todas las reseñas registradas en el sistema");
        return resenaRepository.findAll().stream()
                .map(validaciones::convertirADTO)
                .toList();
    }

    public ResenaDTO traerResenaPorId(Long id) {
        log.info("Buscando reseña con ID: {}", id);
        Resena resena = buscarEntidadPorId(id);
        return validaciones.convertirADTO(resena);
    }

    public ResenaDTO guardarNuevaResena(ResenaDTO datos) {
        log.info("Guardando nueva reseña del cliente ID: {} para el barbero ID: {}", 
                datos.getIdDelClienteQueOpina(), datos.getIdDelBarberoEvaluado());
        Cliente cliente = clienteRepository.findById(datos.getIdDelClienteQueOpina())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado en la base de datos local"));
        
        Barbero barbero = barberoRepository.findById(datos.getIdDelBarberoEvaluado())
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado en la base de datos local"));

        Resena nuevaResena = new Resena();
        nuevaResena.setCliente(cliente);
        nuevaResena.setBarbero(barbero);
        nuevaResena.setCalificacionDeEstrellas(datos.getCalificacionDeEstrellas());
        nuevaResena.setComentarioDelCliente(datos.getComentarioDelCliente());
        nuevaResena.setFechaDeLaResena(datos.getFechaDeLaResena() != null ? datos.getFechaDeLaResena() : LocalDate.now());
        if (!validaciones.validarResena(nuevaResena)) {
            throw new RuntimeException("Error de negocio: La reseña contiene datos inválidos o los identificadores no respondieron al llamado HTTP externo");
        }
        
        return validaciones.convertirADTO(resenaRepository.save(nuevaResena));
    }

    public void eliminarResena(Long id) {
        log.info("Eliminando reseña con ID: {}", id);
        if (!resenaRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la reseña con el ID: " + id);
        }
        resenaRepository.deleteById(id);
    }

    /**
     * Método auxiliar interno para el aislamiento y recuperación de entidades puras
     */
    private Resena buscarEntidadPorId(Long id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la reseña con el ID: " + id));
    }
}