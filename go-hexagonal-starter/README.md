## GymSchedule - Backend

Este es el backend para gestionar los horarios de los gimnasios, siguiendo la arquitectura hexagonal (también conocida como puertos y adaptadores) utilizando Go y el framework Fiber.

## Estructura del Proyecto

La estructura del proyecto está organizada siguiendo los principios de la arquitectura hexagonal, con un fuerte enfoque en la separación de la lógica de negocio (dominio) de los detalles de infraestructura (como la base de datos o las rutas HTTP). Esto permite que los adaptadores puedan cambiar fácilmente sin afectar la lógica central.

## Estructura de Carpetas

```
/go-hexagonal-starter
  ├── /cmd
  │     └── /server
  │           └── main.go         # Punto de entrada de la aplicación (arranque del servidor)
  ├── /internal
  │     ├── /app
  │     │     └── service.go      # Casos de uso de la aplicación (lógica de negocio)
  │     ├── /domain
  │     │     └── gym.go          # Entidades de dominio, lógica del gimnasio
  │     ├── /infrastructure
  │     │     ├── /http
  │     │     │     └── gym_handler.go  # Adaptador para las rutas HTTP
  │     │     └── /db
  │     │           └── gym_repository.go # Adaptador para acceso a base de datos
  └── go.mod
  └── go.sum
```

## Descripción de las Carpetas

- /cmd/server: Esta carpeta contiene el archivo principal (main.go), donde se arranca el servidor y se configuran las rutas de la API. Este es el punto de entrada de la aplicación.

- /internal: Aquí se encuentra el código principal de la aplicación, dividido en las siguientes subcarpetas:

- /app: Contiene los casos de uso o servicios que manejan la lógica de negocio de la aplicación, como la gestión de los horarios de los gimnasios. Los casos de uso no deben depender de la infraestructura.

- /domain: Define las entidades de dominio y la lógica del negocio. En este caso, la entidad Gym que contiene la información del gimnasio (ID, nombre, ubicación y horarios). Esta capa es la más importante, ya que debe contener la lógica central del negocio.

- /infrastructure: Esta carpeta contiene los adaptadores que permiten que la aplicación interactúe con el mundo exterior (base de datos, rutas HTTP, etc.). Los adaptadores deben implementar las interfaces definidas en las capas superiores.

- /http: Contiene los controladores HTTP (handlers) que manejan las solicitudes y respuestas HTTP.
  /db: Contiene los repositorios de acceso a la base de datos (en este ejemplo, un repositorio simulado con un mapa en memoria).

## Explicación de los Componentes

- /cmd/server/main.go: Aquí se arranca el servidor Fiber y se configuran las rutas. La instancia de Fiber interactúa con los servicios de la aplicación a través de los controladores HTTP.

- /internal/app/service.go: Contiene la lógica de negocio. Aquí se definen los servicios (casos de uso) que orquestan las interacciones con el dominio y el repositorio.Por ejemplo, el servicio GymService tiene métodos como GetGymSchedules y AddGymSchedule, que gestionan la obtención y actualización de los horarios de los gimnasios.

- /internal/domain/gym.go: Contiene la definición de las entidades de negocio (en este caso, Gym).
  Aquí definimos la estructura del gimnasio, que incluye el ID, el nombre, la ubicación y los horarios.

- /internal/infrastructure/db/gym_repository.go: Implementa los repositorios que permiten acceder a los datos persistentes. En este ejemplo, usamos un repositorio simulado en memoria (MockGymRepository), que almacena los gimnasios en un mapa.
  Este repositorio es el adaptador para la infraestructura de base de datos.

- /internal/infrastructure/http/gym_handler.go: Los handlers HTTP definen cómo la aplicación responde a las solicitudes externas (como obtener o actualizar horarios). Los controladores HTTP llaman a los servicios de la capa de aplicación y devuelven las respuestas apropiadas.

## Cómo Ejecutar el Proyecto

- #### Requisitos
  - Tener instalado Go 1.18+ en tu máquina.
  - Instalación de Dependencias

#### Clona el repositorio:

```
git clone <url-del-repositorio>
cd go-hexagonal-starter
```

#### Inicializa el módulo Go (si no lo has hecho antes):

```
go mod tidy
```

#### Instala las dependencias:

```
go get -u github.com/gofiber/fiber/v2
```

#### Ejecutar el Proyecto

Para ejecutar el proyecto, solo tienes que correr el siguiente comando:

```
go run cmd/server/main.go
```

Esto arrancará el servidor en el puerto 3000 por defecto.

## Endpoints de la API

GET /gimnasios/:id/horarios
Obtiene los horarios de un gimnasio.

Parámetros: id (ID del gimnasio).
Respuesta:

```
{
  "id": "1",
  "name": "Gimnasio Ejemplo",
  "location": "Ubicación Ejemplo",
  "horarios": ["06:00 AM - 08:00 AM", "09:00 AM - 11:00 AM"]
}
```

POST /gimnasios/:id/horarios
Actualiza los horarios de un gimnasio.

Parámetros: id (ID del gimnasio).
Cuerpo (JSON):

```
["07:00 AM - 09:00 AM", "10:00 AM - 12:00 PM"]
```

Respuesta:

```
{
  "id": "1",
  "name": "Gimnasio Ejemplo",
  "location": "Ubicación Ejemplo",
  "horarios": ["07:00 AM - 09:00 AM", "10:00 AM - 12:00 PM"],
  "status": "Horarios actualizados correctamente"
}
```

## Estructura de la Arquitectura Hexagonal

- Capa de Dominio: Contiene las entidades de negocio y la lógica central de la aplicación. Gym es la entidad principal.

- Capa de Aplicación: Los casos de uso, como GetGymSchedules y AddGymSchedule, gestionan la lógica de la aplicación y orquestan las operaciones.

- Capa de Infraestructura: Adaptadores que interactúan con el mundo exterior, como controladores HTTP y repositorios de base de datos. Aquí es donde se conecta la aplicación con la base de datos o cualquier otro sistema externo.
