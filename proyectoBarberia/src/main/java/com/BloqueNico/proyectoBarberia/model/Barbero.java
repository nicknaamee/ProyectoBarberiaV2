package com.BloqueNico.proyectoBarberia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barbero")
@Data
@NoArgsConstructor  
@AllArgsConstructor 
public class Barbero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_barbero")
    private Long idBarbero;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del barbero debe tener entre 3 y 50 caracteres")
    @Column(name = "nombre_barbero", nullable = false, length = 50)
    private String nombreBarbero;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 3, max = 50, message = "La especialidad debe tener entre 3 y 50 caracteres")
    @Column(name = "especialidad_barbero", nullable = false, length = 50)
    private String especialidadBarbero;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
    @Column(name = "telefono_barbero", nullable = false, length = 15) // ¡OPTIMIZADO! Ajustado al tamaño del @Size
    private String telefonoBarbero;

    @NotNull(message = "El estado del barbero es obligatorio")
    @Column(name = "estado", nullable = false)
    private Boolean estado = true;
}