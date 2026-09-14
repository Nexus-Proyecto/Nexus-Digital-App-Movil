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



**Instituto Superior Politécnico Córdoba (ISPC)**


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