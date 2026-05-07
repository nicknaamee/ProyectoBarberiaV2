package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaCita;

    private Long idDelClienteQueAgenda;
    private Long idDelBarberoAsignado;
    private Long idDelServicioSolicitado;

    private LocalDate fechaDeLaCita;
    private LocalTime horaDeInicio;
    private String estadoDeLaCita;
}

/* CAMBIOS A REVISAR HOY EN CLASES

package com.barberia.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeLaCita;

    @ManyToOne  ---> Así podemos llamar a la tabla barbero de manera más eficaz q haciendo esos atributos de arriba, les tinca?
    @JoinColumn(name = "id_barbero")
    private Barbero barbero;

    @ManyToOne ---> lo mismo q con lo de arriba, así llamamos a la tabla cliente.
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    private LocalDate fechaDeLaCita;
    private LocalTime horaDeInicio;
    private String estadoDeLaCita;
}
    */