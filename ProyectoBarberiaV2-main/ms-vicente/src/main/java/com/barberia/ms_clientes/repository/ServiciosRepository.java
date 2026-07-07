package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// repo que relaciona barbero con sus servicios
@Repository
public interface ServiciosRepository extends JpaRepository<Servicios, Long> {
}
