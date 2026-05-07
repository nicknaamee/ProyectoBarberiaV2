package com.barberia.ms_clientes.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelCliente;

    @Size(min = 3, max = 15, message = "El nombre debe tener al menos 3 carácteres")
    @Column(nullable = false, length = 15)
    private String nombreDelCliente;

    @Size(min = 3, max = 15, message = "El apellido debe tener al menos 3 carácteres")
    @Column(nullable = false, length = 15)
    private String apellidoDelCliente;

    @Column(unique = true)
    private String emailDelCliente;
    
    private String telefonoDelCliente;
    private LocalDate fechaRegistro;

}
