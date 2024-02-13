# API REST RAPTURE AR

Esta es una aplicación CRUD (Crear, Leer, Actualizar, Eliminar) simple conectada a una API Spring Boot. La aplicación permite a los usuarios realizar operaciones CRUD en un recurso a través de puntos finales de API. Además, incluye funcionalidades para autenticación de usuarios (inicio de sesión/cierre de sesión).

## Deendencias

[VER PARTE TECICA DE API REST](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/ApiRestRaptureAR/readme.md)

[VER PARTE TECICA DE ANDROID APP](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/readme.md)

### Tecnologías Utilizadas

- **Spring Boot**: Para construir e implementar la API.
- **Spring Security**: Para autenticación y autorización de usuarios.
- **Spring Data JPA**: Para interactuar con la base de datos.
- **API RESTful**: Para la comunicación entre el frontend y el backend.

### Puntos Finales (Endpoints)

- **GET /product**: Recuperar todos los recursos.
- **GET /product/{id}**: Recuperar un recurso específico por ID.
- **POST /product**: Crear un nuevo recurso.
- **PUT /product/{id}**: Actualizar un recurso existente.
- **DELETE /product/{id}**: Eliminar un recurso.

### Puntos Finales de Autenticación de Usuarios

- **POST /api/auth/login**: Inicio de sesión de usuario.
- **POST /api/auth/logout**: Cierre de sesión de usuario.

## Lógica de Operaciones CRUD

### Crear un Recurso
Para crear un nuevo recurso, el frontend envía una solicitud POST al punto final `/api/recurso` con los datos necesarios en el cuerpo de la solicitud. La API valida los datos, crea el recurso en la base de datos y devuelve una respuesta de éxito con el recurso recién creado.

<img src="https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/3.jpg" width="300" height="500" />
<img src="https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/4.jpg" width="300" height="500" />

### Actualizar un Recurso
Para actualizar un recurso existente, el frontend envía una solicitud PUT al punto final `/api/recurso/{id}` con los datos actualizados en el cuerpo de la solicitud junto con el ID del recurso. La API actualiza el recurso en la base de datos según el ID proporcionado y devuelve una respuesta de éxito.

![5](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/5.jpg)
![6](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/6.jpg)

### Eliminar un Recurso
Para eliminar un recurso, el frontend envía una solicitud DELETE al punto final `/api/recurso/{id}` con el ID del recurso. La API elimina el recurso de la base de datos según el ID proporcionado y devuelve una respuesta de éxito.

![7](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/7.jpg)
![8](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/8.jpg)

### Lógica de Autenticación de Usuarios

### Inicio de Sesión de Usuario
Para iniciar sesión, el usuario envía una solicitud POST al punto final `/api/auth/login` con sus credenciales (por ejemplo, nombre de usuario y contraseña) en el cuerpo de la solicitud. La API verifica las credenciales, genera un JWT (Token de Web JSON) y lo envía de vuelta al cliente. El cliente almacena este token para solicitudes posteriores.

![1](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/1.jpg)
![2](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/2.jpg)

### Cierre de Sesión de Usuario
Para cerrar sesión, el usuario envía una solicitud POST al punto final `/api/auth/logout`. La API invalida el token JWT asociado con la sesión del usuario, lo que efectivamente cierra su sesión.

![9](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/9.jpg)
![10](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/img/10.jpg)

## Configuración y Ejecución de la Aplicación

1. Clona el repositorio.
2. Configura la base de datos y actualiza las configuraciones de la base de datos en `application.properties`.
3. Construye y ejecuta la aplicación Spring Boot.
4. Accede a los puntos finales de la API desde la aplicación frontend.
