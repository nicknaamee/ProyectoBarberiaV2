-- Crea las tablas del microservicio: productos, reseñas y servicios

CREATE TABLE productos (
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_producto VARCHAR(255) NOT NULL,
    cantidad_en_stock INT NOT NULL,
    precio_unitario_del_producto DOUBLE NOT NULL
);

CREATE TABLE productos_sucursal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_sucursal BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE servicios (
    id_del_servicio BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_servicio VARCHAR(255) NOT NULL,
    precio_del_servicio DOUBLE NOT NULL,
    duracion_en_minutos INT NOT NULL
);

CREATE TABLE servicios_barbero (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_barbero BIGINT NOT NULL,
    id_servicio BIGINT NOT NULL,
    disponible BOOLEAN NOT NULL
);

CREATE TABLE resenas (
    id_de_la_resena BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT NOT NULL,
    barbero_id BIGINT NOT NULL,
    calificacion_de_estrellas INT NOT NULL,
    comentario_del_cliente VARCHAR(255),
    fecha_de_la_resena DATE
);

CREATE TABLE resenas_resumen (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    barbero_id BIGINT NOT NULL,
    total_resenas INT NOT NULL,
    promedio_estrellas DOUBLE NOT NULL
);

-- Datos de prueba
INSERT INTO productos (nombre_del_producto, cantidad_en_stock, precio_unitario_del_producto) VALUES
    ('Shampoo anticaspa', 50, 6990.0),
    ('Cera para peinar', 30, 4990.0),
    ('Aceite para barba', 20, 8990.0);

INSERT INTO servicios (nombre_del_servicio, precio_del_servicio, duracion_en_minutos) VALUES
    ('Corte clasico', 8000.0, 30),
    ('Arreglo de barba', 6000.0, 20),
    ('Corte y barba', 12000.0, 45);

INSERT INTO productos_sucursal (id_sucursal, producto_id, stock) VALUES
    (1, 1, 25),
    (1, 2, 15),
    (2, 3, 10);

INSERT INTO servicios_barbero (id_barbero, id_servicio, disponible) VALUES
    (1, 1, TRUE),
    (1, 2, TRUE),
    (2, 3, FALSE);

INSERT INTO resenas (id_cliente, barbero_id, calificacion_de_estrellas, comentario_del_cliente, fecha_de_la_resena) VALUES
    (1, 1, 5, 'Excelente corte, quedo muy bien', '2026-06-01'),
    (2, 1, 4, 'Buena atencion', '2026-06-10');

INSERT INTO resenas_resumen (barbero_id, total_resenas, promedio_estrellas) VALUES
    (1, 2, 4.5),
    (2, 0, 0.0);
