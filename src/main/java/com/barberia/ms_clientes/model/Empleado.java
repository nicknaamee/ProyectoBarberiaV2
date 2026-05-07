package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity 
@Table(name = "empleados") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelEmpleado;

    private String nombreCompleto;
    private String cargoEnLaBarberia;
    private Double salarioMensual;
    private LocalDate fechaDeContrato;
}
