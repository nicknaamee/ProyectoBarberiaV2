package com.BloqueNico.proyectoBarberia.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BloqueNico.proyectoBarberia.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreDelProductoContainingIgnoreCase(String nombre);
}