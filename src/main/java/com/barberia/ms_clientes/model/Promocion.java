package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity 
@Table(name = "promociones") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor

public class Promocion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaPromocion;

    private String descripcionDeLaPromocion;
    private Integer porcentajeDeDescuento;
    private LocalDate fechaInicioDePromocion;
    private LocalDate fechaFinDePromocion;
}
