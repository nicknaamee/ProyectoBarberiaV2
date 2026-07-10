package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Resenas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenasRepository extends JpaRepository<Resenas, Long> {

}
