package com.barberia.ms_productos.service;

import com.barberia.ms_productos.dto.ProveedorDTO;
import com.barberia.ms_productos.model.Proveedor;
import com.barberia.ms_productos.repository.ProveedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProveedorService {

    @Autowired
    private ProveedorRepository repo;

    public List<Proveedor> traerTodos() {
        log.info("buscando todos los proveedores");
        return repo.findAll();
    }

    public Proveedor traerPorId(Long id) {
        log.info("buscando proveedor con id: {}", id);
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("no existe el proveedor con id: " + id));
    }

    public Proveedor guardar(ProveedorDTO datos) {
        log.info("guardando nuevo proveedor: {}", datos.getNombreDelProveedor());
        Proveedor nuevo = new Proveedor();
        nuevo.setNombreDelProveedor(datos.getNombreDelProveedor());
        nuevo.setEmailDeContacto(datos.getEmailDeContacto());
        nuevo.setTelefonoDeContacto(datos.getTelefonoDeContacto());
        nuevo.setProductoQueNosProvee(datos.getProductoQueNosProvee());
        return repo.save(nuevo);
    }

    public Proveedor actualizar(Long id, ProveedorDTO datos) {
        log.info("actualizando proveedor con id: {}", id);
        Proveedor proveedor = traerPorId(id);
        proveedor.setNombreDelProveedor(datos.getNombreDelProveedor());
        proveedor.setEmailDeContacto(datos.getEmailDeContacto());
        proveedor.setTelefonoDeContacto(datos.getTelefonoDeContacto());
        proveedor.setProductoQueNosProvee(datos.getProductoQueNosProvee());
        return repo.save(proveedor);
    }

    public void eliminar(Long id) {
        log.info("eliminando proveedor con id: {}", id);
        traerPorId(id);
        repo.deleteById(id);
    }
}
