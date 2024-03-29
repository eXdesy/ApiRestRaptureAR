# Spring Boot API REST

## Logica principal

### model `Product`:
Este código es una clase en Java que representa un modelo de datos para un producto.

**Resumen:**

- La clase `Product` representa un producto con cuatro atributos: `id` (identificador), `name` (nombre), `price` (precio) y `url` (URL de la imagen del producto).
- Se utiliza la anotación `@NoArgsConstructor` de Lombok para generar un constructor sin argumentos.
- Se utiliza la anotación `@AllArgsConstructor` de Lombok para generar un constructor que acepta todos los argumentos de la clase.
- Se utiliza la anotación `@Data` de Lombok para generar getters y setters, `equals()`, `hashCode()`, y `toString()`.
- Se utiliza la anotación `@Builder` de Lombok para generar un builder para la clase `Product`.

**Explicación detallada:**

- `@NoArgsConstructor`: Esta anotación genera un constructor sin argumentos, lo que significa que puede crear instancias de `Product` sin necesidad de proporcionar valores iniciales para los atributos.
- `@AllArgsConstructor`: Esta anotación genera un constructor que acepta todos los argumentos de la clase, lo que facilita la inicialización de objetos `Product` con valores para todos los atributos.
- `@Data`: Esta anotación de Lombok genera automáticamente los métodos `getters` y `setters` para todos los campos de la clase, así como los métodos `equals()`, `hashCode()`, y `toString()`, lo que reduce la cantidad de código que se necesita escribir.
- `@Builder`: Esta anotación genera un método `builder()` que permite construir objetos `Product` de una manera más fluida y legible, utilizando un patrón de diseño de builder.

### dto `ProductDTO`:
Este código es una clase Java en el lenguaje de programación orientado a objetos. Parece ser parte de una aplicación que maneja productos, y está destinada a representar un DTO (Data Transfer Object) para los productos. Los DTOs son objetos utilizados para transportar datos entre diferentes capas de una aplicación, generalmente entre el back-end y el front-end.

**Resumen:**

- Representa un DTO para los productos. Tiene los siguientes campos.
- `name`: Un String que representa el nombre del producto.
- `description`: Un String que representa la descripcion del producto.
- `price`: Un entero que representa el precio del producto.
- `url`: Un String que representa la URL de la imagen del producto.


**Explicación detallada:**

   - `@AllArgsConstructor`: Genera automáticamente un constructor que acepta todos los campos de la clase como argumentos.
   - `@Builder`: Genera un patrón de diseño de constructor de creación de objetos (Builder pattern), que permite construir objetos paso a paso y facilita la creación de objetos complejos.
   - `@Data`: Genera automáticamente los métodos `toString()`, `equals()`, `hashCode()`, `getters`, `setters` para todos los campos de la clase.
   - `@NoArgsConstructor`: Genera automáticamente un constructor sin argumentos.

### controller `ProductController`:
El código proporcionado es un controlador RESTful en Java utilizando el framework Spring Boot para gestionar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una colección de productos. Aquí hay un resumen y una explicación del código:

1. **Definición del Controlador:**
   - Se define la clase `ProductController` con la anotación `@RestController`, indicando que esta clase manejará las solicitudes HTTP y devolverá los resultados directamente a través del cuerpo de respuesta.
   - Se utiliza la anotación `@CrossOrigin` para permitir solicitudes CORS (Cross-Origin Resource Sharing), lo que permite que este controlador sea accedido desde diferentes dominios.

2. **Mapeo de Endpoints:**
   - Los endpoints son definidos utilizando las anotaciones `@GetMapping`, `@PostMapping`, `@PutMapping` y `@DeleteMapping`, que corresponden a las operaciones HTTP GET, POST, PUT y DELETE respectivamente.
   - La raíz de este controlador es `/product`.

3. **Operaciones CRUD:**
   - `getAllProducts()`: Devuelve todos los productos almacenados.
   - `getById(int id)`: Devuelve un producto específico según su ID.
   - `create(ProductDTO dto)`: Crea un nuevo producto utilizando los datos proporcionados en el cuerpo de la solicitud.
   - `update(int id, ProductDTO dto)`: Actualiza un producto existente con los datos proporcionados.
   - `delete(int id)`: Elimina un producto según su ID.

4. **Modelo y DTO:**
   - El modelo de datos `Product` representa un producto con atributos como ID, nombre, precio y URL de imagen.
   - Se utiliza un DTO (Objeto de Transferencia de Datos) `ProductDTO` para transferir datos entre el cliente y el servidor. Esto proporciona una capa adicional de abstracción y seguridad, separando el modelo de dominio del modelo de transferencia de datos.

5. **Datos de Ejemplo:**
   - Se proporciona una lista inicial de productos en el constructor de la clase, pero en una aplicación real, estos datos podrían provenir de una base de datos u otro almacenamiento persistente.


