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
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 15, message = "El nombre no puede superar los 15 caracteres") // Alinhado con tu restricción SQL
    @Column(nullable = false, length = 15)
    private String nombreCliente;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    @Size(max = 15, message = "El apellido no puede superar los 15 caracteres") // Alinhado con tu restricción SQL
    @Column(nullable = false, length = 15)
    private String apellidoCliente;

    @NotBlank(message = "El email del cliente es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido") // Valida la estructura lógica con '@' y '.'
    @Column(unique = true)
    private String emailCliente;
    
    @NotBlank(message = "El teléfono del cliente es obligatorio")
    @Size(min = 9, max = 12, message = "El teléfono debe tener entre 9 y 12 dígitos") // Control de largo de tu DTO
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números") // Asegura que no metan letras
    private String telefonoCliente;

    @NotNull(message = "La fecha de registro es obligatoria")
    @PastOrPresent(message = "La fecha de registro no puede ser una fecha futura")
    private LocalDate fechaRegistro;

}