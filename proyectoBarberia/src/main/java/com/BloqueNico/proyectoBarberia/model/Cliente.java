package com.BloqueNico.proyectoBarberia.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, length = 15)
    @NotBlank(message="el nombre del cliente es obligatorio")
    private String nombreCliente;

    @Column(nullable = false, length = 15)
    @NotBlank(message="el apellido del cliente es obligatorio")
    private String apellidoCliente;

    @Column(unique = true)
    @Email(message="el email debe tener formato valido: ejemplo@correo.com")
    @NotBlank(message = "el email es obligatorio")
    private String emailCliente;
    
    @NotBlank(message="El teléfono es obligatorio")
    @Size(min = 9, max = 12, message = "el telefono debe tener entre 9 y 12 caracteres")
    @Pattern(regexp = "^[0-9+]+$", message = "El teléfono solo puede contener números y el signo +") 
    @Column(name = "telefono_cliente", nullable = false, length = 12)
    private String telefonoCliente;

    @NotNull(message = "La fecha de registro es obligatoria")
    @PastOrPresent(message = "La fecha de registro no puede ser una fecha futura")
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

}
