
#  Android App

## Logica Principal:

- **`CreateFragment`:**

![fragment_create](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/fragment_create.png)

- 	**`onCreate(Bundle savedInstanceState)`**: Este método es llamado cuando el fragmento está siendo creado. En este caso, se llama al método `onCreate()` de la clase padre.

	```
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	```

- **`onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)`**: Este método infla el diseño del fragmento y devuelve la vista. Luego inicializa los elementos de la interfaz de usuario, como EditText y Button, y configura un OnClickListener para el botón.

	```
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        // Inicializar los EditText
        nameText = rootView.findViewById(R.id.editTextNombre);
        priceText = rootView.findViewById(R.id.editTextPrecio);
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen);
        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonCrearProducto);
        // Puedes agregar un listener al botón si deseas manejar clicks
        button.setOnClickListener(new View.OnClickListener() {
            // Método onClick para manejar clicks en el botón
            @Override
            public void onClick(View v) {
                // Obtener el texto del EditText del nombre
                String nombre = nameText.getText().toString();
                // Obtener el texto del EditText del precio
                String precioString = priceText.getText().toString();
                // Obtener el texto del EditText de la URL de la imagen
                String urlImagen = editTextUrlImagen.getText().toString();
                // Verificar si el campo de precio está vacío
                if (precioString.isEmpty()) {
                    return;
                }
                // Convertir el precio a flotante
                float precio = Float.parseFloat(precioString);
                // Crear un objeto ProductDTO con los datos obtenidos
                ProductDTO dto = new ProductDTO(nombre, precio, urlImagen);
                // Llamar al método create con el objeto ProductDTO
                create(dto);
            }
        });
        // Retornar la vista inflada
        return rootView;
    }
    ```

- **`create(ProductDTO dto)`**: Este método crea un nuevo producto utilizando Retrofit. Se construye una instancia de Retrofit y se crea una llamada para crear un producto en el servidor remoto. Luego, la llamada se encola para ejecutarse de manera asíncrona. Se definen dos métodos de devolución de llamada para manejar la respuesta del servidor: onResponse y onFailure.

	```
	private void create(ProductDTO dto){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(HomeFragment.IPHost).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        // Crear una instancia de la interfaz CRUDInterface
        crudInterface= retrofit.create(CRUDInterface.class);
        // Crear una llamada para crear un producto
        Call<Product> call = crudInterface.create(dto);

        // Encolar la llamada para ejecutarla de manera asíncrona
        call.enqueue(new Callback<Product>() {
            // Método onResponse para manejar la respuesta del servidor
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                // Verificar si la respuesta no fue exitosa
                if(!response.isSuccessful()){
                    // Registrar un error en el Log
                    Log.e("Response err ",response.message());
                    // Salir del método si la respuesta no fue exitosa
                    return;
                }
                // Obtener el producto de la respuesta
                Product product=response.body();
                // Mostrar un Toast indicando que el producto fue añadido correctamente
                mostrarToast("Producto añadido correctamente: " + product.getName());
            }
            // Método onFailure para manejar fallos en la llamada
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                /// Registrar un error en el Log
                Log.e("Throw err:",t.getMessage());
            }
        });
    }
	```

- **`mostrarToast(String mensaje)`**: Este método muestra un mensaje Toast en la actividad actual con el mensaje proporcionado.

	```
	    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
	```

- **`DeleteFragment`**

![fragment_delete](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/fragment_delete.png)

- **`onCreate(Bundle savedInstanceState)`**: Método de creación del fragmento. Llama al método `onCreate()` de la clase padre.

```
// Método de creación del fragmento@Overridepublic void onCreate(Bundle savedInstanceState) {    // Llama al método onCreate de la superclass    super.onCreate(savedInstanceState);}
```

	- **`onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)`**: 
Método para crear la vista del fragmento. Infla el diseño del fragmento desde el archivo XML `fragment_delete`, inicializa el `EditText` para el ID y configura el botón de borrado llamando al método `setupDeleteButton()`.

```
// Método para crear la vista del fragmento@Overridepublic View onCreateView(LayoutInflater inflater, ViewGroup container,                         Bundle savedInstanceState) {    View view = inflater.inflate(R.layout.fragment_delete, container, false);    // Inicializar el EditText para el ID    idEditText = view.findViewById(R.id.editTextID);    // Configurar el botón de borrado con el click    setupDeleteButton(view);    return view;}
```

	- **`setupDeleteButton(View view)`**: 
Método para configurar el botón de borrado. Obtiene una referencia al botón de borrado desde la vista, y le asigna un `OnClickListener` que se activa cuando se hace clic en el botón. En el clic, obtiene el ID ingresado por el usuario y llama al método `delete()` para eliminar el producto correspondiente.

```
// Método para configurar el botón de borradoprivate void setupDeleteButton(View view) {    button = view.findViewById(R.id.buttonBorrarProducto);    button.setOnClickListener(new View.OnClickListener() {        @Override        public void onClick(View v) {            // Obtener el ID ingresado por el usuario            String idString = idEditText.getText().toString().trim();            // Verifica si el ID no está vacío            if (!idString.isEmpty()) {                int productId = Integer.parseInt(idString);                delete(productId);            } else {                // Manejar el caso en el que el ID esté vacío                Log.e("Error", "El ID no puede estar vacío");            }        }    });}
```

	- **`delete(int id)`**: 
Método para borrar un producto. Construye una instancia de Retrofit, crea la interfaz `CRUDInterface`, y llama al método `delete()` de esta interfaz con el ID del producto. Maneja la respuesta mediante un `Callback`, mostrando un mensaje de éxito si la eliminación es exitosa o registrando un error si falla.

```
// Método para borrar un productoprivate void delete(int id) {    // Construir la instancia de Retrofit    Retrofit retrofit = new Retrofit.Builder()            .baseUrl(HomeFragment.IPHost)            .addConverterFactory(GsonConverterFactory.create())            .build();    // Crear la interfaz CRUDInterface    crudInterface = retrofit.create(CRUDInterface.class);    // Llamar al método de borrado con el ID del producto    Call<Void> call = crudInterface.delete(id);    call.enqueue(new Callback<Void>() {        @Override        public void onResponse(Call<Void> call, Response<Void> response) {            if (!response.isSuccessful()) {                Log.e("Response err ", response.message());                return;            }            mostrarToast("Producto eliminado");            // Borrado exitoso, puedes realizar acciones adicionales si es necesario        }        @Override        public void onFailure(Call<Void> call, Throwable t) {            Log.e("Throw err:", t.getMessage());        }    });}
```

	- **`mostrarToast(String mensaje)`**: 
Método para mostrar un `Toast`. Crea y muestra un `Toast` con un mensaje pasado como argumento. Este mensaje es una notificación corta que aparece en la parte inferior de la pantalla y se desvanece automáticamente después de un corto período de tiempo.

```
// Método para mostrar un Toasprivate void mostrarToast(String mensaje) {    Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();}
```

- **`ExitFragment`**

![fragment_exit](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/fragment_exit.png)

	- **`@Override public void onCreate(Bundle savedInstanceState)`**: Este método se llama cuando se crea el fragmento. En este caso, se llama al método `onCreate()` de la superclase (`Fragment`) para realizar cualquier inicialización necesaria.

```
@Overridepublic void onCreate(Bundle savedInstanceState) {    super.onCreate(savedInstanceState);}
```

	- **`@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)`**: 
Este método se llama para que el fragmento cree su diseño de interfaz de usuario. Aquí, se infla el diseño del fragmento utilizando el `LayoutInflater`. También se configura un `OnClickListener` para un botón dentro del diseño del fragmento.

```
@Overridepublic View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    // Inflar el diseño del fragmento    View view = inflater.inflate(R.layout.fragment_exit, container, false);    // Aquí puedes agregar cualquier lógica adicional que necesites para el fragmento de salida    firebaseAuth=FirebaseAuth.getInstance();    // Cuando el usuario hace click en el botón de salida, cierra la aplicación    view.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {        // Llamar al método logOut() cuando el usuario hace click en el botón de salida        @Override        public void onClick(View v) {            logOut();        }    });    return view;}
```

	- **`private void logOut()`**: 
Este método realiza la funcionalidad de cerrar sesión. Llama al método `signOut()` de la instancia de `FirebaseAuth` para cerrar sesión del usuario actual y luego llama al método `backToLogin()` para redirigir al usuario a la pantalla de inicio de sesión.

```
private void logOut() {    // Cerrar sesión del usuario actual utilizando FirebaseAuth    firebaseAuth.signOut();    // Llamar al método backToLogin() para volver a la actividad de inicio de sesión    backToLogin();}
```
	- **`private void backToLogin()`**: 
Este método crea un `Intent` para iniciar la actividad de inicio de sesión (`LoginActivity`) y luego inicia esa actividad utilizando `startActivity()`. Es utilizado para redirigir al usuario a la pantalla de inicio de sesión después de cerrar sesión.

```
// Método para volver a la actividad de inicio de sesiónprivate void backToLogin(){    Intent intent = new Intent(getActivity(), LoginActivity.class);    startActivity(intent);}
```

- **`HomeFragment`**

![fragment_home](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/fragment_home.png)

	- **`onCreate(Bundle savedInstanceState)`**: 
Este método es un método de ciclo de vida de un `Fragment` en Android. Se llama cuando el fragmento está siendo creado. En este caso, simplemente llama al método `onCreate()` de la superclase.

```
@Overridepublic void onCreate(Bundle savedInstanceState) {    super.onCreate(savedInstanceState);}
```

	- **gelAll()`**: 
Este método es privado y se encarga de realizar una llamada a la API para obtener todos los productos. Utiliza Retrofit para realizar la llamada asíncrona a la interfaz de la API definida en `CRUDInterface`. Cuando se obtiene una respuesta exitosa, se configura un adaptador de productos y se muestra la lista de productos en la vista de lista. En caso de error, se registra el error en el registro de eventos.

```
private void gelAll(){    // Método para obtener todos los productos    Retrofit retrofit=new Retrofit.Builder().baseUrl(IPHost).            addConverterFactory(GsonConverterFactory.create()).build();    // Crea una instancia de Retrofit para realizar llamadas a la API    crudInterface=retrofit.create(CRUDInterface.class);    // Crea una instancia de la interfaz CRUDInterface    Call<List<Product>> call=crudInterface.getAll();    // Realiza una llamada para obtener todos los productos    call.enqueue(new Callback<List<Product>>() {        @Override        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {            // Se ejecuta cuando la llamada es exitosa            if(!response.isSuccessful()){                Log.e("Response err ",response.message());                return;            }            // Si la respuesta no es exitosa, registra un error en el registro de eventos            productList=response.body();            // Obtiene la lista de productos de la respuesta            ProductAdapter productAdapter = new ProductAdapter(requireContext(), productList);            // Crea un adaptador de productos            listView.setAdapter(productAdapter);            // Establece el adaptador en la vista de lista            productList.forEach(p-> Log.i("Productos: ",p.toString()));            // Registra cada producto en el registro de eventos        }        @Override        public void onFailure(Call<List<Product>> call, Throwable t) {            // Se ejecuta cuando la llamada falla            Log.e("Throw err:", Objects.requireNonNull(t.getMessage()));            // Registra el error en el registro de eventos        }    });}
```

	- **`onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)`**: 
Este método es un método de ciclo de vida de un `Fragment` en Android. Se llama cuando es el momento de crear y devolver la vista asociada con el fragmento. En este caso, infla el diseño de la interfaz de usuario del fragmento desde el archivo XML `fragment_home.xml`, encuentra la vista de lista en el diseño y luego llama al método `gelAll()` para obtener los productos. Finalmente, devuelve la vista creada.

```
@Overridepublic View onCreateView(LayoutInflater inflater, ViewGroup container,                         Bundle savedInstanceState) {    // Se llama cuando se crea la vista del fragmento    View view = inflater.inflate(R.layout.fragment_home, container, false);    // Infla el diseño de la interfaz de usuario del fragmento    listView = view.findViewById(R.id.listView);    // Busca la vista de lista en el diseño de la interfaz de usuario    gelAll();    // Llama al método para obtener todos los productos    return view;    // Devuelve la vista creada}
```

- **`LoginActivity`**

![activity_login](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/activity_login.png)

	- **`onCreate(Bundle savedInstanceState)`**: 
 Este método es llamado cuando la actividad está siendo creada. Aquí se inicializan los elementos de la interfaz de usuario, se configuran los listeners de eventos y se realizan otras tareas de inicialización necesarias para la actividad.

```
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user =findViewById(R.id.editTextText);
        password =findViewById(R.id.editTextTextPassword);

        dbHelper = new DBHelper(this);

        mAuth=FirebaseAuth.getInstance();
        signInButton= findViewById(R.id.btnGoogle);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }
```

	- **`onStart()`**: 
 Este método es llamado cuando la actividad se vuelve visible para el usuario. Aquí se verifica si el usuario ya ha iniciado sesión anteriormente (a través de Firebase Auth) y se actualiza la interfaz de usuario en consecuencia.

```
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
```

	- **`onActivityResult(int requestCode, int resultCode, Intent data)`**: 
 Este método es llamado cuando se recibe un resultado de otra actividad, en este caso, después de que el usuario realiza un inicio de sesión con Google. Aquí se manejan los resultados de la solicitud de inicio de sesión de Google y se autentica al usuario con Firebase Auth.

```
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.e(TAG, "Google Sign In failed. Error code: " + e.getStatusCode());
            }
        }
    }
```

	- **`firebaseAuthWithGoogle(String idToken)`**: 
 Este método realiza la autenticación del usuario con Firebase Auth utilizando el token de identificación proporcionado por Google después del inicio de sesión exitoso. Se crea una credencial con el token de identificación y se utiliza para iniciar sesión con Firebase Auth.

```
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.e(TAG, "Firebase authentication failed.", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }
```

	- **`updateUI(FirebaseUser user)`**: 
 Este método actualiza la interfaz de usuario según el estado del usuario actual. Si el usuario está autenticado, se redirige a la siguiente actividad (en este caso, `NavigationActivity`). Si el usuario no está autenticado, no se realiza ninguna acción.

```
    private void updateUI(FirebaseUser user) {
        user= mAuth.getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(this, NavigationActivity.class);
            startActivity(intent);
        }
    }
```

	- **`login(View view)`**: 
 Este método se llama cuando el usuario intenta iniciar sesión manualmente ingresando un nombre de usuario y contraseña. Verifica las credenciales ingresadas llamando al método `validateLogin(String username, String password)`. Si las credenciales son válidas, se redirige al usuario a la actividad de navegación. Si las credenciales no son válidas, se muestra un mensaje de error.

```
    public void login(View view){
        String username = this.user.getText().toString();
        String password = this.password.getText().toString();
        if (validateLogin(username, password)) {
            Intent intent = new Intent(this, NavigationActivity.class);
            intent.putExtra("usuario", username);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
```

	- **`validateLogin(String username, String password)`**: 
 Este método verifica si las credenciales ingresadas por el usuario son válidas consultando la base de datos local (`DBHelper`). Realiza una consulta para buscar un usuario con el nombre de usuario y contraseña proporcionados. Si encuentra una coincidencia, devuelve `true`, lo que indica que las credenciales son válidas.

```
    private boolean validateLogin(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DBHelper.TABLE_USERS,
                new String[]{DBHelper.COLUMN_USERNAME},
                DBHelper.COLUMN_USERNAME + " = ? AND " + DBHelper.COLUMN_PASSWORD + " = ?",
                new String[]{username, password},
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
```

- **`NavigationActivity`**

![navigationmenu](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/navigationmenu.png)

	- **`onCreate(Bundle savedInstanceState)`**: 
Este método es llamado cuando la actividad está empezando. Es donde se debe realizar la inicialización de la actividad, como la creación de la interfaz de usuario (llamando a setContentView()) y la lógica de inicialización. Recibe un parámetro Bundle savedInstanceState, que es un objeto que proporciona datos acerca del estado de la actividad previamente guardado, en caso de que la actividad se haya reanudado después de haber sido destruida.

```
protected void onCreate(Bundle savedInstanceState) { // Método onCreate con un parámetro de tipo Bundle llamado savedInstanceState    super.onCreate(savedInstanceState); // Llama al método onCreate de la clase padre    setContentView(R.layout.navigationmenu); // Establece el contenido de la actividad a partir del layout definido en navigationmenu.xml    onNavigationItemSelectedListener(); // Llama al método onNavigationItemSelectedListener}
```

	- **`onNavigationItemSelectedListener()`**:
Este método personalizado se encarga de inicializar y configurar el comportamiento de la vista BottomNavigationView. Busca la vista BottomNavigationView por su ID, configura el ítem seleccionado y establece un listener para manejar eventos de selección de ítems en la vista. Cuando se selecciona un ítem, se navega a los diferentes fragmentos correspondientes según el ítem seleccionado.

```
protected void onNavigationItemSelectedListener(){ // Método onNavigationItemSelectedListener    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView); // Declara e inicializa una instancia de BottomNavigationView buscando su ID en el layout    bottomNavigationView.setSelectedItemId(R.id.navigation_home); // Establece el ítem seleccionado en la vista BottomNavigationView como la opción "navigation_home"    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment); // Obtiene el fragmento de navegación anidado en el layout    NavController navController = navHostFragment.getNavController(); // Obtiene el controlador de navegación del fragmento    bottomNavigationView.setOnItemSelectedListener(item -> { // Establece un Listener para manejar eventos de selección en la BottomNavigationView        if (item.getItemId() == R.id.navigation_home) { // Si el ítem seleccionado es "navigation_home"            navController.navigate(R.id.homeFragment); // Navega al fragmento llamado "homeFragment"        } else if (item.getItemId() == R.id.navigation_create) { // Si el ítem seleccionado es "navigation_create"            navController.navigate(R.id.createFragment); // Navega al fragmento llamado "createFragment"        } else if (item.getItemId() == R.id.navigation_update) { // Si el ítem seleccionado es "navigation_update"            navController.navigate(R.id.updateFragment); // Navega al fragmento llamado "updateFragment"        }else if (item.getItemId() == R.id.navigation_delete) { // Si el ítem seleccionado es "navigation_delete"            navController.navigate(R.id.deleteFragment); // Navega al fragmento llamado "deleteFragment"        }else if (item.getItemId() == R.id.navigation_exit) { // Si el ítem seleccionado es "navigation_exit"            navController.navigate(R.id.exitFragment); // Navega al fragmento llamado "exitFragment"        }        return true; // Devuelve verdadero para indicar que el evento de selección fue manejado    });}
```

- **`UpdateFragment`**

![fragment_update](https://github.com/eXdesy/ApiRestRaptureAR/blob/master/RaptureAR/img/fragment_update.png)

	- **`onCreateView()`**: 
Este método se llama cuando el fragmento necesita crear su diseño de vista. Dentro de este método, se infla el diseño del fragmento a partir de un archivo de diseño XML, se inicializan los componentes de la interfaz de usuario (EditText, Button) y se configuran los listeners de eventos, como el clic en el botón.

```
@Overridepublic View onCreateView(LayoutInflater inflater, ViewGroup container, // Método que infla el diseño del fragmento                         Bundle savedInstanceState) {    // Inflar el diseño del fragmento    View rootView = inflater.inflate(R.layout.fragment_update, container, false);    // Inicializar los EditText    idText = rootView.findViewById(R.id.editTextID); // Obtener el EditText para el ID del producto    nameText = rootView.findViewById(R.id.editTextNombre); // Obtener el EditText para el nombre del producto    priceText = rootView.findViewById(R.id.editTextPrecio); // Obtener el EditText para el precio del producto    editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen); // Obtener el EditText para la URL de la imagen del producto    retrofit = new Retrofit.Builder().baseUrl(HomeFragment.IPHost) // Configurar Retrofit con la URL base            .addConverterFactory(GsonConverterFactory.create()) // Agregar el convertidor Gson            .build(); // Construir el objeto Retrofit    // Inicializar el botón    button = rootView.findViewById(R.id.buttonActualizarProducto); // Obtener el botón de actualización del producto    // Agregar un listener al botón para manejar clicks    button.setOnClickListener(new View.OnClickListener() {        @Override        public void onClick(View v) {            actualizar(); // Llamar al método de actualización        }    });    return rootView; // Devolver la vista inflada}
```

	- **`actualizar()`**: 
Este método se llama cuando se hace clic en el botón para actualizar un producto. Obtiene los valores ingresados en los campos de texto (nombre, precio, URL de la imagen) y crea un objeto `ProductDTO` con estos valores. Luego, utiliza Retrofit para crear una instancia de la interfaz `CRUDInterface` y llama al método `actualizar` con el DTO y el ID del producto. Este método también maneja las respuestas y los errores de la llamada Retrofit.

```
private void actualizar() { // Método para actualizar el producto    String id = idText.getText().toString().trim(); // Obtener el ID del producto    String nombre = nameText.getText().toString().trim(); // Obtener el nombre del producto    String precio = priceText.getText().toString().trim(); // Obtener el precio del producto    String imagen = editTextUrlImagen.getText().toString().trim(); // Obtener la URL de la imagen del producto    // Crear un objeto ProductDTO en lugar de Product    ProductDTO productDTO = new ProductDTO(nombre, Float.parseFloat(precio), imagen);    crudInterface = retrofit.create(CRUDInterface.class); // Crear una instancia de CRUDInterface    // Llamar al método actualizar con el DTO y el ID    Call<Product> call = crudInterface.actualizar(Integer.parseInt(id), productDTO);    call.enqueue(new Callback<Product>() { // Realizar la llamada de manera asíncrona        @Override        public void onResponse(Call<Product> call, Response<Product> response) { // Manejar la respuesta            if (!response.isSuccessful()) { // Si la respuesta no es exitosa                Log.e("Response err ", response.message()); // Registrar un error                return;            }            Product product = response.body(); // Obtener el producto actualizado            // Hacer algo con el producto actualizado si es necesario            mostrarToast("Producto actualizado: " + product.getName()); // Mostrar un mensaje emergente con el nombre del producto actualizado        }        @Override        public void onFailure(Call<Product> call, Throwable t) { // Manejar el fallo de la llamada            Log.e("Throw err:", t.getMessage()); // Registrar un error            mostrarToast("Error al actualizar el producto"); // Mostrar un mensaje emergente de error        }    });}
```

	- **`mostrarToast()`**: 
Este método muestra un mensaje emergente (Toast) en la pantalla. Recibe un mensaje como parámetro y utiliza `Toast.makeText()` para crear un mensaje emergente corto con el mensaje proporcionado.

```
// Método para mostrar un Toastprivate void mostrarToast(String mensaje) { // Método para mostrar un mensaje emergente    Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show(); // Mostrar un Toast con el mensaje proporcionado}
```

## Packpages:
	- **adapters `ProductAdapter`**: 
El código proporciona un adaptador personalizado (`ProductAdapter`) para mostrar una lista de productos en una aplicación de Android. Utiliza una clase interna (`ViewHolder`) para mejorar el rendimiento al reciclar vistas. El método `getView()` infla la vista de cada elemento de la lista y asigna los datos del producto correspondiente a las vistas. La biblioteca Picasso se utiliza para cargar imágenes desde una URL en el `ImageView`.

	- **db `DBHelper`**: 
Este código proporciona una implementación básica de una clase DBHelper que administra una base de datos SQLite en una aplicación Android. 

	- **dto `ProductDTO`**: 
Este código define una clase `ProductDTO` que representa un objeto de transferencia de datos (DTO) para un producto. Contiene tres atributos: `name` (nombre del producto), `price` (precio del producto) y `imageUrl` (URL de la imagen del producto). Además, proporciona un constructor para inicializar estos atributos, así como métodos getter y setter para acceder y modificar los valores de los atributos. En resumen, esta clase encapsula los datos relacionados con un producto y proporciona métodos para interactuar con estos datos de manera segura y coherente.

	- **inerfaz `CRUDInterface`**: 
Esta interfaz proporciona métodos para interactuar con una API RESTful que gestiona productos, permitiendo realizar operaciones básicas de CRUD sobre ellos.

	- **model `Product`**: 
El código define una clase llamada `Product`, que representa un producto en un sistema. La clase tiene cuatro atributos: `productID` (ID del producto), `name` (nombre del producto), `price` (precio del producto) e `imageUrl` (URL de la imagen del producto). Además de los constructores y los métodos getter y setter para acceder y modificar estos atributos, la clase también incluye un método `toString()` que devuelve una representación en cadena de texto de los valores de los atributos del producto. En resumen, la clase `Product` encapsula los datos relacionados con un producto y proporciona métodos para acceder y manipular esos datos.
