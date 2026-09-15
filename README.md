## Proyecto Aplicación Móvil | Tecnicatura Superior en Desarrollo Web y Aplicaciones Digitales | ISPC




# Nexus Digital - Aplicación Móvil




Aplicación móvil nativa de Nexus Digital, desarrollada para dispositivos Android como extensión de la plataforma de compra y venta de productos a nivel local en la provincia de Córdoba.




## Integrantes




- **Griselda Leonor Aguirre** — Scrum Master / Developer
- **Juan Ignacio Alonso** — Product Owner / Developer
- **Yohana Eugenia Grosso** — Developer
- **Laura Molina** — Developer
- **Augusto Andrés Raffaeli** — Developer
- **María Soledad Scarlata** — Developer

## Descripción





Nexus Digital es una plataforma de compra y venta de productos a nivel local en la provincia de Córdoba.





La aplicación móvil busca trasladar las principales funcionalidades de la plataforma web al entorno Android, permitiendo a los usuarios acceder al catálogo, consultar productos, gestionar su cuenta y realizar las operaciones disponibles desde un dispositivo móvil.





## Problema que Resuelve





En la provincia de Córdoba, muchos emprendedores, comerciantes locales y particulares enfrentan dificultades para visibilizar sus productos en el entorno digital, lo que limita sus oportunidades de venta.





Nexus Digital busca facilitar el acceso al comercio local mediante una aplicación móvil sencilla y accesible, permitiendo que compradores y vendedores puedan interactuar desde sus dispositivos Android.



## Pantallas / Activities


La aplicación estará organizada inicialmente en las siguientes pantallas principales:


| Integrante | Pantalla / Activity    | Clase Java              | Layout XML              | Responsabilidad                                                     |#
| ---------- | ---------------------- | ----------------------- | ----------------------- | ------------------------------------------------------------------- |
| Laura Molina     | Splash / Bienvenida    | `SplashActivity.java`   | `activity_splash.xml`   | Pantalla inicial y validación de sesión.                            |
| Soledad Scarlata      | Login                  | `LoginActivity.java`    | `activity_login.xml`    | Autenticación del usuario mediante la API.                          |
| Juan Alonso    | Registro               | `RegisterActivity.java` | `activity_register.xml` | Registro de nuevos usuarios mediante la API.                        |
| Yohana Grosso     | Catálogo / Inicio      | `HomeActivity.java`     | `activity_home.xml`     | Visualización del catálogo de productos.                            |
| Griselda Aguirre       | Detalle del producto   | `DetailActivity.java`   | `activity_detail.xml`   | Visualización de la información completa del producto seleccionado. |
| Andrés Raffaelli   | Perfil / Configuración | `ProfileActivity.java`  | `activity_profile.xml`  | Visualización de los datos del usuario y cierre de sesión.          |




## Funcionalidades Principales


La aplicación móvil permitirá:
* Registrarse.
* Iniciar sesión.
* Consultar el catálogo de productos.
* Visualizar el detalle de un producto.
* Consultar la información del usuario.
* Cerrar sesión.
* Comunicarse con el backend mediante la API REST.
  




## Requerimientos Funcionales


| Código   | Requisito Funcional                                                                                                                                                                                        |
| -------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **RF01** | La aplicación debe permitir a los usuarios registrarse ingresando sus datos personales y credenciales.                                                                                                     |
| **RF02** | La aplicación debe permitir a los usuarios iniciar sesión mediante sus credenciales.                                                                                                                       |
| **RF03** | La aplicación debe permitir visualizar el catálogo de productos disponibles.                                                                                                                               |
| **RF04** | La aplicación debe permitir al usuario seleccionar un producto desde el catálogo y visualizar su información detallada, transfiriendo el identificador del producto entre las Activities correspondientes. |
| **RF05** | La aplicación debe permitir visualizar la información del usuario autenticado.                                                                                                                             |
| **RF06** | La aplicación debe permitir cerrar la sesión del usuario.                                                                                                                                                  |
| **RF07** | La aplicación debe permitir comunicarse con el backend mediante una API REST para obtener y enviar información.                                                                                            |
| **RF08** | La aplicación debe permitir navegar entre las distintas Activities y regresar desde una Activity hija a su Activity padre correspondiente.                                                                 |




## Requerimientos No Funcionales


| Código    | Requisito No Funcional                                                                                                         |
| --------- | ------------------------------------------------------------------------------------------------------------------------------ |
| **RNF01** | La aplicación debe adaptarse correctamente a diferentes tamaños y resoluciones de dispositivos Android.                        |
| **RNF02** | La aplicación debe ofrecer una interfaz intuitiva y adecuada para la interacción mediante pantalla táctil.                     |
| **RNF03** | La aplicación debe proteger las credenciales y los datos sensibles de los usuarios.                                            |
| **RNF04** | La aplicación debe presentar una estructura modular y organizada que facilite su mantenimiento y futuras modificaciones.       |
| **RNF05** | La aplicación debe gestionar adecuadamente los errores de comunicación con la API y proporcionar información clara al usuario. |
| **RNF06** | La aplicación debe ofrecer tiempos de respuesta adecuados durante las operaciones realizadas por el usuario.                   |



## Entorno de Desarrollo

## Para el desarrollo y las pruebas se utilizarán:

Android Studio.
JDK 17.
Android SDK.
Dispositivos Android físicos.
Cable USB de datos para ejecutar y probar la aplicación desde Android Studio.

## Conexión con Nexus Digital Web

La aplicación móvil forma parte del proyecto Nexus Digital y reutiliza el backend desarrollado previamente.

La comunicación seguirá el siguiente flujo:

Usuario
│
Aplicación Android
│
API REST
│
Django REST Framework
│
MySQL

Esto permite que la aplicación móvil utilice los servicios y datos proporcionados por el backend de Nexus Digital.

## Instalación

## Requisitos previos
Android Studio instalado.
JDK 17.
Android SDK configurado.
Dispositivo Android para pruebas o emulador.
Acceso al backend de Nexus Digital.

## Pruebas

La aplicación será probada inicialmente en dispositivos Android físicos mediante conexión USB y utilizando las herramientas de depuración proporcionadas por Android Studio.

## Documentación

La documentación correspondiente al proyecto se encontrará disponible en la wiki del repositorio.