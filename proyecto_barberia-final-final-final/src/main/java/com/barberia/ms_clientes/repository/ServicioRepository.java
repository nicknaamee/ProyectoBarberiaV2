package com.barberia.ms_clientes.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barberia.ms_clientes.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository <Servicio, Long> {

}
