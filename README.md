# Nexus Digital - Aplicación Móvil

Aplicación móvil nativa para Android desarrollada como extensión de **Nexus Digital**, una plataforma de comercio electrónico orientada a compradores y vendedores.

Proyecto realizado en la **Tecnicatura Superior en Desarrollo Web y Aplicaciones Móviles – ISPC**.

## Integrantes

| Integrante                  | Rol                       |
| --------------------------- | ------------------------- |
| **Griselda Leonor Aguirre** | Scrum Master / Developer |
| **Juan Ignacio Alonso**     | Product Owner / Developer  |
| **Yohana Eugenia Grosso**   | Developer                 |
| **Laura Molina**            | Developer                 |
| **Augusto Andrés Raffaeli** | Developer                 |
| **María Soledad Scarlata**  | Developer                 |

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
La aplicación móvil forma parte del proyecto Nexus Digital y reutiliza el backend desarrollado previamente.
Esto permite que la aplicación móvil utilice los servicios y datos proporcionados por el backend de Nexus Digital.


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
| `ContactActivity`  | Información de contacto |

## Funcionalidades

* Registro e inicio de sesión.
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

## Entorno de Desarrollo

### Para el desarrollo y las pruebas se utilizarán:

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
* Acceso al backend de Nexus Digital.

### Pruebas

La aplicación será probada inicialmente en dispositivos Android físicos mediante conexión USB y utilizando las herramientas de depuración proporcionadas por Android Studio.

### Documentación

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

La documentación completa se encuentra en la **Wiki del repositorio**.