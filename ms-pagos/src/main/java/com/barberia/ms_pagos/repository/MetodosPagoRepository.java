package com.barberia.ms_pagos.repository;

import com.barberia.ms_pagos.model.MetodosPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodosPagoRepository extends JpaRepository<MetodosPago, Long> {

}
