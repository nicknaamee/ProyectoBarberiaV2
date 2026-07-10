package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long> {

}
