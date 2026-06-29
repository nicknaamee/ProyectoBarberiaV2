# Segunda entrega - Sistema de Barbería (Microservicios)

## Integrantes
* Leandro Araya
* Nicolás Poblete
* Vicente Saez

## De qué trata el proyecto
Esta es nuestra segunda entrega de la evaluación de la asignatura Desarrollo FullStack 1. Es un sistema para gestionar una barbería dividido en microservicios independientes que se comunican entre sí a través de REST, usando un API Gateway para centralizar las llamadas y Eureka para que los servicios se descubran automáticamente.

### ¿Qué diferencia hay con la entrega pasada?
En la entrega anterior se trabajó como un microservicio monolítico. Ahora está dividido en microservicios
Para esta Entrega 3, mejoramos el sistema añadiendo:
1. **Controladores V2 con HATEOAS:** Ahora las respuestas de la API no son solo datos sueltos, sino que incluyen enlaces hipermedia (formato HAL JSON) para enseñarle al cliente qué otras acciones puede hacer (como ver el detalle de la cita o facturarla).
2. **Pruebas Unitarias:** Creamos test con JUnit 5 y Mockito para Citas y Facturas en la carpeta `src/test/java`, asegurando que el sistema responda bien con un 80% de cobertura y maneje los errores cuando un ID no existe.
3. **Migración con Flyway:** Activamos el control de versiones de la base de datos mediante scripts SQL en la carpeta de migración.

## Listado de Microservicios implementados
* **ms-gateway:** El API Gateway que recibe todas las peticiones externas.
* **ms-eureka:** El servidor de descubrimiento donde se registran los servicios.
* **ms-citas:** Gestiona el agendamiento, estados y borrado de citas de la barbería.
* **ms-vicente:** Gestiona productos, servicios y reseñas de la barbería
* **proyectoBarberia:** Gestiona usuarios y autenticación


## Cómo ejecutar el proyecto en local
1. Levantar la base de datos local en MySQL
2. Primero, dar iniciar el microservicio `ms-eureka` desde el IDE
3. Segundo, iniciar el microservicio `ms-gateway`
4. Por último, encender los microservicios de negocio ms-citas, ms-vicente y proyectoBarberia