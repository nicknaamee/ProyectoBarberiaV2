# ms_clientes — Microservicio para gestionar una barbería

Este es un proyecto de microservicio hecho con Spring Boot que sirve para manejar todo lo que pasa dentro de una barbería: los clientes que van a cortarse el pelo, los barberos que trabajan, las citas que se agendan, los productos que se venden, las reseñas que dejan los clientes, y otras cosas relacionadas.

La idea es tener una API a la que se le puedan hacer peticiones desde una página web o una aplicación móvil para crear, leer, actualizar o borrar la información de la barbería.

> **Nota:** este proyecto todavía está en desarrollo. Algunas partes funcionan bien y otras siguen siendo trabajadas por el equipo, así que puede que encuentres cosas a medio terminar.

---

## ¿Qué hace este proyecto?

El microservicio se encarga de guardar y entregar información sobre:

- **Clientes** — la gente que va a la barbería
- **Barberos** — quienes cortan el pelo, con su especialidad y si están trabajando o no
- **Citas** — las reservas que hace un cliente con un barbero para un día y hora específicos
- **Servicios** — lo que ofrece la barbería (corte de pelo, afeitado, etc.) con su precio y duración
- **Productos** — cosas que se venden dentro del local (shampoo, cera, etc.)
- **Reseñas** — las opiniones que dejan los clientes sobre los barberos
- **Facturas** — el cobro que se hace cuando termina una cita
- **Reportes** — resúmenes con los ingresos y otra información del negocio
- **Métodos de pago** — efectivo, tarjeta, transferencia, etc.

Cada uno de estos elementos tiene su propia "sección" en la API, con sus propias rutas para crear, consultar, modificar o eliminar.

---

## ¿Qué tecnologías usa?

Estas son las herramientas principales que necesitas conocer (o por lo menos saber que existen) para trabajar con el proyecto:

- **Java 17** — el lenguaje de programación
- **Spring Boot 3** — el framework que nos da casi todo hecho para crear el servidor web
- **Spring Data JPA** — para guardar y leer cosas de la base de datos sin tener que escribir mucho SQL
- **Lombok** — una librería que escribe automáticamente los `getters`, `setters` y constructores, así nuestro código queda más corto y limpio
- **Hibernate Validator** — para revisar que los datos que llegan estén bien (por ejemplo, que un email tenga formato de email)
- **Maven** — la herramienta que descarga las dependencias del proyecto y lo compila

---

## ¿Cómo correr el proyecto?

### Antes de empezar, necesitas tener instalado:

1. **JDK 17 o superior** — para que Java funcione en tu computadora
2. **Maven** — para construir el proyecto (si tienes IntelliJ o VS Code con extensiones de Java, normalmente ya viene incluido)
3. **Una base de datos** — el proyecto está configurado para usar MySQL, pero también funciona con H2 (que es una base de datos en memoria, súper útil para hacer pruebas)
4. **Git** — para descargar el proyecto

### Paso 1: descargar el proyecto

Abre una terminal y escribe:

```bash
git clone https://github.com/tu-usuario/ms_clientes.git
cd ms_clientes
```

### Paso 2: configurar la base de datos

Abre el archivo `src/main/resources/application.properties` y revisa que las líneas de conexión estén bien para tu computadora. Algo así:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/barberia
spring.datasource.username=tu_usuario
spring.datasource.password=tu_clave
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Si no quieres instalar MySQL, puedes usar H2 cambiando esas líneas por:

```properties
spring.datasource.url=jdbc:h2:mem:barberia
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Con H2 no necesitas instalar nada extra y la base de datos se crea sola cada vez que arranca el proyecto (los datos se pierden al apagar, pero para probar está perfecto).

### Paso 3: construir el proyecto

Desde la carpeta del proyecto, escribe en la terminal:

```bash
./mvnw clean install
```

En Windows sería:

```bash
mvnw.cmd clean install
```

Este comando descarga todas las librerías que necesita el proyecto y deja todo listo para correr.

### Paso 4: arrancar el servidor

```bash
./mvnw spring-boot:run
```

Si todo sale bien, vas a ver un montón de texto en la consola y al final algo como:

```
Tomcat started on port(s): 8080 (http)
Started MsClientesApplication in 4.523 seconds
```

Eso significa que el servidor está corriendo en `http://localhost:8080`. Si abres esa dirección en el navegador no vas a ver una página bonita (porque es una API, no una web), pero ya está lista para recibir peticiones.

---

## Endpoints disponibles

Aquí va una lista de las rutas más importantes que tiene la API. Todas empiezan con `http://localhost:8080`.

Para probarlas puedes usar herramientas como **Postman**, **Insomnia** o **Thunder Client** (esta última es una extensión de VS Code).

### Clientes

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/clientes`       | Trae la lista de todos los clientes |
| GET    | `/api/v1/clientes/{id}`  | Trae un cliente específico por su id |
| POST   | `/api/v1/clientes`       | Crea un cliente nuevo |
| PUT    | `/api/v1/clientes/{id}`  | Actualiza la información de un cliente |
| DELETE | `/api/v1/clientes/{id}`  | Borra un cliente |

Ejemplo del cuerpo (JSON) para crear un cliente:

```json
{
  "nombreCliente": "Juan",
  "apellidoCliente": "Pérez",
  "emailCliente": "juan.perez@correo.com",
  "telefonoCliente": "912345678"
}
```

### Barberos

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/barberos`                       | Lista todos los barberos |
| GET    | `/api/v1/barberos/{id}`                  | Trae un barbero por id |
| GET    | `/api/v1/barberos/especialidad/{esp}`    | Filtra barberos por especialidad |
| GET    | `/api/v1/barberos/estado/{status}`       | Filtra barberos por si están activos o no |
| POST   | `/api/v1/barberos`                       | Crea un barbero nuevo |
| PUT    | `/api/v1/barberos/{id}`                  | Actualiza un barbero |
| DELETE | `/api/v1/barberos/{id}`                  | Elimina un barbero |

### Citas

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/citas`                  | Lista todas las citas |
| GET    | `/api/v1/citas/barbero/{id}`     | Lista las citas de un barbero específico |
| GET    | `/api/v1/citas/estado?estado=X`  | Filtra citas por su estado (pendiente, confirmada, etc.) |
| POST   | `/api/v1/citas/agendar`          | Crea una cita nueva |
| PUT    | `/api/v1/citas/actualizar/{id}`  | Modifica los datos de una cita |
| PATCH  | `/api/v1/citas/{id}/estado`      | Cambia solo el estado de una cita |
| DELETE | `/api/v1/citas/{id}`             | Borra una cita |

### Servicios

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/servicios`        | Lista todos los servicios |
| GET    | `/api/v1/servicios/{id}`   | Trae un servicio por id |
| POST   | `/api/v1/servicios`        | Crea un servicio |
| PUT    | `/api/v1/servicios/{id}`   | Actualiza un servicio |
| DELETE | `/api/v1/servicios/{id}`   | Elimina un servicio |

### Productos

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/productos`        | Lista todos los productos |
| GET    | `/api/v1/productos/{id}`   | Trae un producto por id |
| POST   | `/api/v1/productos`        | Crea un producto |
| PUT    | `/api/v1/productos/{id}`   | Actualiza un producto |
| DELETE | `/api/v1/productos/{id}`   | Borra un producto |

### Reseñas

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/resenas`        | Lista todas las reseñas |
| GET    | `/api/v1/resenas/{id}`   | Trae una reseña específica |
| POST   | `/api/v1/resenas`        | Crea una reseña nueva |
| DELETE | `/api/v1/resenas/{id}`   | Borra una reseña |

### Facturas

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| POST   | `/api/v1/facturas/emitir?idCita=X&monto=Y&metodo=Z` | Genera una factura para una cita ya realizada |

### Reportes

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/reportes`        | Lista todos los reportes |
| GET    | `/api/v1/reportes/{id}`   | Trae un reporte específico |
| POST   | `/api/v1/reportes`        | Crea un reporte nuevo |
| PUT    | `/api/v1/reportes/{id}`   | Modifica un reporte |
| DELETE | `/api/v1/reportes/{id}`   | Elimina un reporte |

### Métodos de pago

| Método | Ruta | Lo que hace |
|--------|------|-------------|
| GET    | `/api/v1/metodos-pago`        | Lista los métodos de pago disponibles |
| GET    | `/api/v1/metodos-pago/{id}`   | Trae un método de pago por id |
| POST   | `/api/v1/metodos-pago`        | Crea un método de pago nuevo |
| PUT    | `/api/v1/metodos-pago/{id}`   | Actualiza un método de pago |
| DELETE | `/api/v1/metodos-pago/{id}`   | Elimina un método de pago |

---

## Códigos de respuesta que vas a ver

Cuando hagas una petición, la API te va a contestar con un número que indica si todo salió bien o no:

- **200 OK** — todo bien, aquí está la información que pediste
- **201 Created** — se creó correctamente el elemento nuevo
- **204 No Content** — se borró o actualizó bien, no hay nada que devolverte
- **400 Bad Request** — los datos que enviaste están mal (falta un campo, formato incorrecto, etc.)
- **404 Not Found** — no se encontró lo que pediste (por ejemplo, un cliente con un id que no existe)
- **409 Conflict** — hay un problema con las reglas del negocio (por ejemplo, intentar crear un cliente con un email que ya está usado)
- **500 Internal Server Error** — algo se rompió en el servidor, normalmente es un error que hay que revisar en el código

---

## Cómo probar que funciona

Después de arrancar el servidor, puedes hacer una prueba rápida desde la terminal con `curl`:

```bash
curl http://localhost:8080/api/v1/clientes
```

Si te devuelve `[]` (una lista vacía) significa que todo está funcionando, solo que todavía no hay datos guardados.

Para crear un cliente de prueba:

```bash
curl -X POST http://localhost:8080/api/v1/clientes \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCliente": "María",
    "apellidoCliente": "González",
    "emailCliente": "maria@correo.com",
    "telefonoCliente": "987654321"
  }'
```

---

## Estado del proyecto

**El proyecto está en desarrollo activo.** Esto significa que algunas funcionalidades están terminadas, otras a medio hacer y puede que encuentres cosas que todavía no funcionen del todo bien. El equipo está trabajando en mejorar y unificar las distintas partes que cada integrante implementó en sus propias ramas.

Si encuentras algún error o algo raro, lo mejor es comentarlo con el equipo antes de asumir que es un problema tuyo.
