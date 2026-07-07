
CREATE TABLE barberos (
    id_barbero BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_barbero VARCHAR(50) NOT NULL,
    especialidad_barbero VARCHAR(50) NOT NULL,
    telefono_barbero VARCHAR(50) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE clientes (
    id_cliente BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(15) NOT NULL,
    apellido_cliente VARCHAR(15) NOT NULL,
    email_cliente VARCHAR(255) UNIQUE,
    telefono_cliente VARCHAR(255),
    fecha_registro DATE
);

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
    
    CONSTRAINT fk_resena_cliente 
        FOREIGN KEY (id_opinion_cliente) 
        REFERENCES clientes(id_cliente),
        
    CONSTRAINT fk_resena_barbero 
        FOREIGN KEY (id_barbero_evaluado) 
        REFERENCES barberos(id_barbero)
);