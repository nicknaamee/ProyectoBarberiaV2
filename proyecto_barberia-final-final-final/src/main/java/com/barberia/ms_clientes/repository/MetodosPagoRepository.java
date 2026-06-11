package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.MetodosPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodosPagoRepository extends JpaRepository<MetodosPago, Long> {

}
