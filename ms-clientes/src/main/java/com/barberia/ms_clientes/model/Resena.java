package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity 
@Table(name = "resenas") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Resena {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaResena;

    private Long idDelClienteQueOpina;
    private Long idDelBarberoEvaluado;
    private Integer calificacionDeEstrellas;
    private String comentarioDelCliente;
    private LocalDate fechaDeLaResena;
}
