## Proyecto Aplicación Móvil | Tecnicatura Superior en Desarrollo Web y Aplicaciones Digitales | ISPC




# Nexus Digital - Aplicación Móvil




Aplicación móvil nativa de Nexus Digital, desarrollada para dispositivos Android como extensión de la plataforma de compra y venta de productos a nivel local en la provincia de Córdoba.




## Integrantes




- **Griselda Leonor Aguirre** — Product Owner / Developer
- **Juan Ignacio Alonso** — Scrum Master / Developer
- **Yohana Eugenia Grosso** — Developer
- **Laura Molina** — Developer
- **Augusto Andrés Raffaeli** — Developer
- **María Soledad Scarlata** — Developer

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
  #




## Requerimientos Funcionales


| Código   | Requisito  Funcional |
--------------------------------------------------------------------------------------------------------------------------------------------------- |
| **RF01** | La aplicación debe permitir a los usuarios registrarse ingresando sus datos personales y credenciales.|
| **RF02** | La aplicación debe permitir a los usuarios iniciar sesión mediante sus credenciales.                  |
| **RF03** | La aplicación debe permitir visualizar el catálogo de productos disponibles.                          |
| **RF04** | La aplicación debe permitir al usuario seleccionar un producto desde el catálogo y visualizar su información detallada, transfiriendo el identificador del producto entre las Activities correspondientes.|
| **RF05** | La aplicación debe permitir visualizar la información del usuario autenticado.                        |
| **RF06** | La aplicación debe permitir cerrar la sesión del usuario.                                             |
| **RF07** | La aplicación debe permitir comunicarse con el backend mediante una API REST para obtener y enviar información.  |
| **RF08** | La aplicación debe permitir navegar entre las distintas Activities y regresar desde una Activity hija a su Activity padre correspondiente. |




## Requerimientos No Funcionales


| Código    | Requisito No Funcional                                                                                                         |
| --------- | ------------------------------------------------------------------------------------------------------------------------------ |
| **RNF01** | La aplicación debe adaptarse correctamente a diferentes tamaños y resoluciones de dispositivos Android.                        |
| **RNF02** | La aplicación debe ofrecer una interfaz intuitiva y adecuada para la interacción mediante pantalla táctil.                     |
| **RNF03** | La aplicación debe proteger las credenciales y los datos sensibles de los usuarios.                                            |
| **RNF04** | La aplicación debe presentar una estructura modular y organizada que facilite su mantenimiento y futuras modificaciones.       |
| **RNF05** | La aplicación debe gestionar adecuadamente los errores de comunicación con la API y proporcionar información clara al usuario. |
| **RNF06** | La aplicación debe ofrecer tiempos de respuesta adecuados durante las operaciones realizadas por el usuario.                   |


