package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

}
