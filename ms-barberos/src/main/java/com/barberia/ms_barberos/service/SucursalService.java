package com.barberia.ms_barberos.service;

import com.barberia.ms_barberos.dto.SucursalDTO;
import com.barberia.ms_barberos.model.Sucursal;
import com.barberia.ms_barberos.repository.SucursalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SucursalService {

    @Autowired
    private SucursalRepository repo;

    public List<Sucursal> traerTodos() {
        log.info("buscando todas las sucursales");
        return repo.findAll();
    }

    public Sucursal traerPorId(Long id) {
        log.info("buscando sucursal con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe la sucursal con id: " + id));
    }

    public Sucursal guardar(SucursalDTO datos) {
        log.info("guardando nueva sucursal: {}", datos.getNombreDeLaSucursal());
        Sucursal nuevo = new Sucursal();
        nuevo.setNombreDeLaSucursal(datos.getNombreDeLaSucursal());
        nuevo.setDireccionCompleta(datos.getDireccionCompleta());
        nuevo.setTelefonoDeLaSucursal(datos.getTelefonoDeLaSucursal());
        nuevo.setEstaAbierta(datos.getEstaAbierta());
        return repo.save(nuevo);
    }

    public Sucursal actualizar(Long id, SucursalDTO datos) {
        log.info("actualizando sucursal con id: {}", id);
        Sucursal sucursal = traerPorId(id);
        sucursal.setNombreDeLaSucursal(datos.getNombreDeLaSucursal());
        sucursal.setDireccionCompleta(datos.getDireccionCompleta());
        sucursal.setTelefonoDeLaSucursal(datos.getTelefonoDeLaSucursal());
        sucursal.setEstaAbierta(datos.getEstaAbierta());
        return repo.save(sucursal);
    }

    public void eliminar(Long id) {
        log.info("eliminando sucursal con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
