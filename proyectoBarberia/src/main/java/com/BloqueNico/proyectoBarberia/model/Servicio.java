package com.BloqueNico.proyectoBarberia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio") 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_del_servicio")
    private Long idDelServicio;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del servicio debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre_del_servicio", nullable = false, length = 100)
    private String nombreDelServicio;

    @NotNull(message = "El precio del servicio es obligatorio")
    @Positive(message = "El precio del servicio debe ser un valor mayor a cero")
    @Column(name = "precio_del_servicio", nullable = false)
    private Double precioDelServicio;

    @NotNull(message = "La duración en minutos es obligatoria")
    @Min(value = 1, message = "La duración en minutos debe ser de al menos 1 minuto")
    @Column(name = "duracion_en_minutos", nullable = false)
    private Integer duracionEnMinutos;
}