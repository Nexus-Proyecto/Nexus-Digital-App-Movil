## Proyecto Aplicación Móvil | Tecnicatura Superior en Desarrollo Web y Aplicaciones Digitales | ISPC




# Nexus Digital - Aplicación Móvil

Aplicación móvil nativa para Android desarrollada como extensión de **Nexus Digital**, una plataforma de comercio electrónico orientada a compradores y vendedores.

Proyecto realizado en la **Tecnicatura Superior en Desarrollo Web y Aplicaciones Digitales – ISPC**.

## Integrantes

| Integrante                  | Rol                       |
| --------------------------- | ------------------------- |
| **Griselda Leonor Aguirre** | Scrum Master / Dev / Tester / Analista de Ciberseguridad  |
| **Juan Ignacio Alonso**     | Product Owner /Dev / Tester / Analista de Ciberseguridad |
| **Yohana Eugenia Grosso**   | Dev / Tester / Analista de Ciberseguridad                 |
| **Laura Molina**            | Dev / Tester / Analista de Ciberseguridad                 |
| **Augusto Andrés Raffaeli** | Dev / Tester / Analista de Ciberseguridad                  |
| **María Soledad Scarlata**  | Dev / Tester / Analista de Ciberseguridad                 |

## Descripción

Nexus Digital es una plataforma de compra y venta de productos a nivel local en la provincia de Córdoba.

La aplicación móvil busca trasladar las principales funcionalidades de la plataforma web al entorno Android, permitiendo a los usuarios acceder al catálogo, consultar productos, gestionar su cuenta y realizar las operaciones disponibles desde un dispositivo móvil.

## Tecnologías

* Android Studio
* Java
* JDK 17
* Android SDK
* API REST
* Django REST Framework
* MySQL
* Autenticación con JWT
* Git / GitHub

## Arquitectura

La aplicación Android funciona como cliente del backend existente:

```text
Usuario
   ↓
Aplicación Android
   ↓
API REST
   ↓
Django REST Framework
   ↓
MySQL
```

La aplicación móvil forma parte del proyecto Nexus Digital y reutiliza el backend desarrollado previamente. Esto permite que la aplicación utilice los servicios y datos proporcionados por el backend de Nexus Digital.

## Activities

| Activity           | Funcionalidad           |
| ------------------ | ----------------------- |
| `SplashActivity`   | Pantalla inicial        |
| `LoginActivity`    | Inicio de sesión        |
| `RegisterActivity` | Registro de usuario     |
| `HomeActivity`     | Catálogo de productos   |
| `DetailActivity`   | Detalle del producto    |
| `ProfileActivity`  | Perfil del usuario      |
| `CartActivity`     | Carrito de compras      |
| `ContactoActivity` | Información de contacto |

## Funcionalidades

* Registro e inicio de sesión, con elección de rol (comprador o vendedor).
* Catálogo y detalle de productos.
* Perfil y cierre de sesión.
* Navegación entre pantallas.
* Pantalla de contacto.
* Comunicación con el backend mediante API REST.
* CRUD de productos para vendedores.
* Búsqueda y filtros de productos.
* Carrito de compras.
* Modificación y eliminación de productos del carrito.
* Actualización de stock.
* Validación de solicitudes y respuestas.
* Manejo de errores de conexión.

## Roles de usuario

Al registrarse, la persona elige su rol:

| Rol           | Qué puede hacer                                                                                               |
| ------------- | ------------------------------------------------------------------------------------------------------------- |
| **Comprador** | Ver el catálogo y el detalle de productos, usar el carrito y confirmar compras.                               |
| **Vendedor**  | Lo mismo que el comprador y, además, publicar, editar y eliminar **sus propios** productos.                   |

La contraseña debe tener al menos 8 caracteres, ser alfanumérica e incluir un carácter especial. Estas reglas se validan en la app y también en el backend.

## Historias de Usuario

El proyecto contempla **13 Historias de Usuario (HU01–HU13)**, desde el registro y navegación hasta la integración con el servidor, gestión de productos, búsqueda, carrito y confirmación de compras.

Los requerimientos funcionales detallados **RF01–RF19** se encuentran documentados en el IEEE830 y en la Wiki del proyecto.

## Sprints

### Sprint 1

* Configuración del proyecto Android.
* Creación de Activities y layouts.
* Navegación.
* Detalle de producto.
* Pantalla de contacto.
* Organización de GitHub y documentación.
* Revisión inicial del backend.

### Sprint 2

* Integración Android con API REST.
* CRUD de productos.
* Búsqueda y filtros.
* Carrito de compras.
* Confirmación de compra.
* Generación de órdenes y actualización de stock.
* Pruebas y seguridad.

## Control de versiones

```text
main
  └── develop
       └── ramas personales / funcionalidades
```

Se utilizan **Issues, Milestones, Projects, Pull Requests y Wiki** para organizar el trabajo colaborativo.

## Entorno de desarrollo

Para el desarrollo y las pruebas se utilizan:

* Android Studio.
* JDK 17.
* Android SDK.
* Dispositivos Android físicos.
* Cable USB de datos para ejecutar y probar la aplicación desde Android Studio.

## Instalación

### Requisitos previos

* Android Studio instalado.
* JDK 17.
* Android SDK configurado.
* Dispositivo Android para pruebas o emulador.
* Python y MySQL para levantar el backend.
* Celular y computadora conectados a la **misma red Wi-Fi**.

### 1. Clonar el repositorio de la app

```bash
git clone https://github.com/Nexus-Proyecto/Nexus-Digital-App-Movil.git
```

### 2. Levantar el backend

La app consume la API REST de Django, que está en el repositorio de Nexus Digital: https://github.com/Nexus-Proyecto/Nexus-Digital

```bash
cd Backend
python -m venv env

# Windows
env\Scripts\activate
# Linux / macOS
source env/bin/activate

pip install -r requirements.txt
```

Crear un archivo `.env` en la carpeta del backend con estas variables. Los valores son propios de cada computadora y **no se suben al repositorio**:

```text
SECRET_KEY=
DEBUG=True
ALLOWED_HOSTS=localhost,127.0.0.1,TU_IP
DB_NAME=
DB_USER=
DB_PASSWORD=
DB_HOST=
DB_PORT=
```

Aplicar las migraciones y levantar el servidor escuchando en toda la red:

```bash
python manage.py migrate
python manage.py runserver 0.0.0.0:8000
```


### 3. Obtener la IP de la computadora

En una terminal de Windows ejecutar `ipconfig` y copiar la **Dirección IPv4** del adaptador Wi-Fi (por ejemplo `192.168.1.42`).

> No usar IPs de adaptadores virtuales (VirtualBox, WSL, etc.): el celular no puede alcanzarlas.

### 4. Configurar la IP en la app

Abrir `app/src/main/java/com/ispc/nexusdigital/api/ApiClient.java` y reemplazar `TU_IP`:

```java
private static final String BASE_URL = "http://TU_IP:8000/";
```

> Este cambio es **local**: no se incluye en los commits ni en los Pull Requests.

### 5. Ejecutar la app

1. Abrir el proyecto en Android Studio y esperar el *Sync* de Gradle.
2. Conectar el celular por USB y presionar **Run**.
3. Iniciar sesión con un usuario de prueba, o registrarse eligiendo comprador o vendedor.

## Pruebas

La aplicación se prueba en dispositivos Android físicos mediante conexión USB, utilizando las herramientas de depuración de Android Studio.

También se incluyen pruebas automatizadas: pruebas unitarias y pruebas de interfaz con Espresso para el flujo de inicio de sesión, con chequeos automáticos de accesibilidad.

## Documentación

La documentación del proyecto incluye:

* IEEE830
* Historias de Usuario
* Requerimientos Funcionales
* Planning Poker
* Daily Scrum
* Sprint Planning
* Sprint Review
* Sprint Retrospective
* Casos de prueba
* Evidencias de desarrollo
* Plan de Seguridad
* Plan de Prueba de Software

La documentación completa se encuentra en la **[Wiki del repositorio](https://github.com/Nexus-Proyecto/Nexus-Digital-App-Movil/wiki)**.











                                                                                                              
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                

