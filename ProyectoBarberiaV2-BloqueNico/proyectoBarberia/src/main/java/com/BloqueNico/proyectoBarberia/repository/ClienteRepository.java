package com.BloqueNico.proyectoBarberia.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.BloqueNico.proyectoBarberia.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> { 

    Optional<Cliente> findByEmailCliente(String email);
    
    boolean existsByEmailCliente(String email);

    Optional<Cliente> findByTelefonoCliente(String telefono);
    
}
