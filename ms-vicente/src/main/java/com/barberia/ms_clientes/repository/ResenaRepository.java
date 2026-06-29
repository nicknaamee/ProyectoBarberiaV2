package com.barberia.ms_clientes.repository;

import com.barberia.ms_clientes.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// repo de reseñas, busca por barbero o por cliente
@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

    List<Resena> findByBarberoId(Long barberoId);

    List<Resena> findByIdCliente(Long idCliente);
}
