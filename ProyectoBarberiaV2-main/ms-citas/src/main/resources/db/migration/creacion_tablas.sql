CREATE TABLE cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    id_barbero BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    id_servicio BIGINT NOT NULL,
    fecha_cita DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    estado_cita VARCHAR(50) NOT NULL
);

-- // public class Cita {
--     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
--     private Long idCita;

--     @JoinColumn(name = "id_barbero")
--     private Long idBarbero;

--     @JoinColumn(name = "id_cliente")
--     private Long idCliente;

--     private LocalDate fechaCita;
--     private LocalTime horaInicio;
--     private String estadoCita;

--     @JoinColumn(name = "id_servicio")
--     private Long idServicio;
-- }


CREATE TABLE factura (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(15) NOT NULL,
    apellido_cliente VARCHAR(15) NOT NULL,
    email_cliente VARCHAR(255) UNIQUE,
    telefono_cliente VARCHAR(255),
    fecha_registro DATE
);

-- public class Factura {
--     @Id
--     @GeneratedValue(strategy = GenerationType.IDENTITY)
--     private Long idFactura;

--     @JoinColumn(name = "id_cita", nullable = false, unique = true)
--     private Cita cita;

--     @Column(nullable = false)
--     private Double montoTotal;

--     @Column(nullable = false)
--     private String metodoDePago;

--     @Column(nullable = false)
--     private LocalDateTime fechaEmision;
-- }



CREATE TABLE productos (
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_producto VARCHAR(255),
    cantidad_en_stock INT,
    precio_unitario_del_producto DOUBLE
);

CREATE TABLE reportes (
    id_del_reporte BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_de_reporte VARCHAR(255),
    total_de_ingresos_calculado DOUBLE,
    fecha_del_reporte DATE,
    resumen_del_reporte VARCHAR(255)
);

CREATE TABLE servicios (
    id_del_servicio BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_servicio VARCHAR(255),
    precio_del_servicio DOUBLE,
    duracion_en_minutos INT
);

CREATE TABLE resenas (
    id_de_la_resena BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_opinion_cliente BIGINT,
    id_barbero_evaluado BIGINT,
    calificacion_de_estrellas INT,
    comentario_del_cliente VARCHAR(255),
    fecha_de_la_resena DATE,

)