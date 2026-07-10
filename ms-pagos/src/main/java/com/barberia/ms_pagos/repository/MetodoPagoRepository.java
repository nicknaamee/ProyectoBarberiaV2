package com.barberia.ms_pagos.repository;

import com.barberia.ms_pagos.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

}
