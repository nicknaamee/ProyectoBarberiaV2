package com.BloqueNico.proyectoBarberia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BloqueNico.proyectoBarberia.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreDelProductoContainingIgnoreCase(String nombre);
}