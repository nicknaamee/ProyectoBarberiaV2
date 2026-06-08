package com.barberia.ms_productos.repository;

import com.barberia.ms_productos.model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {

}
