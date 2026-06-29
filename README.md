# ms-productos — Microservicio de barbería (productos, reseñas y servicios)

Microservicio Spring Boot que gestiona **productos**, **reseñas** y **servicios** de la barbería. Se comunica con los microservicios de clientes y barberos para enriquecer las reseñas.

## Tecnologías

- Java 21
- Spring Boot 4.0.6
- Spring Data JPA
- Spring WebFlux (WebClient para llamar a otros microservicios)
- Spring Cloud Netflix Eureka Client
- Flyway (creación de tablas)
- Spring HATEOAS
- SpringDoc OpenAPI (Swagger)
- Lombok
- MySQL

## Estructura

```
src/main/java/com/barberia/ms_clientes/
├── MsClientesApplication.java
├── config/
│   ├── SwaggerConfig.java
│   └── WebClientConfig.java        ← WebClient @LoadBalanced + CORS
├── controller/v1/                  ← API REST (productos, reseñas, servicios)
├── dto/                            ← incluye ClienteExternoDTO y BarberoExternoDTO
├── exception/
├── model/
├── repository/
└── service/                        ← cada dominio: XxxService + XxxValidaciones

src/main/resources/
├── application.yml                 ← perfiles dev / test / prod + eureka + flyway
└── db/migration/V1__crear_tablas.sql   ← crea las tablas (base de datos)
```

Cada servicio está separado en **Service** (lógica CRUD) y **Validaciones** (validar datos, convertir a DTO y conectar con microservicios externos), siguiendo el patrón del profesor.

## Entidades

| Dominio | Entidad principal | Tabla auxiliar |
|---------|-------------------|----------------|
| Productos | `Producto` (productos) | `Productos` (productos_sucursal) |
| Reseñas | `Resena` (resenas) | `Resenas` (resenas_resumen) |
| Servicios | `Servicio` (servicios) | `Servicios` (servicios_barbero) |

## Endpoints v1

| Método | Ruta |
|--------|------|
| CRUD | `/api/v1/productos` |
| CRUD | `/api/v1/productos-sucursal` |
| CRUD | `/api/v1/servicios` |
| CRUD | `/api/v1/servicios-barbero` |
| GET/POST/DELETE | `/api/v1/resenas` |
| CRUD | `/api/v1/resenas-resumen` |

## Cómo correr

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Requiere MySQL en `localhost:3306` y, para el registro en red, el servidor Eureka en `localhost:8761`.
