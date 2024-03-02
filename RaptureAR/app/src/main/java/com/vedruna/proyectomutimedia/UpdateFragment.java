package com.vedruna.proyectomutimedia;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vedruna.proyectomutimedia.dto.ProductDTO;
import com.vedruna.proyectomutimedia.interfaz.CRUDInterface;
import com.vedruna.proyectomutimedia.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para actualizar un producto.
 */
public class UpdateFragment extends Fragment {
    EditText nameText; // Campo de texto para el nombre del producto
    EditText priceText; // Campo de texto para el precio del producto
    EditText editTextUrlImagen; // Campo de texto para la URL de la imagen del producto
    EditText descriptionText; // Campo de texto para la descripcion del producto
    Button button; // Botón para actualizar el producto
    EditText idText; // Campo de texto para el ID del producto (nuevo)

    private Retrofit retrofit; // Objeto Retrofit para realizar peticiones HTTP
    CRUDInterface crudInterface; // Interfaz para realizar operaciones CRUD

    /**
     * Constructor público vacío requerido.
     */
    public UpdateFragment() {
        // Constructor vacío de la clase UpdateFragment
    }

    /**
     * Método que infla el diseño del fragmento.
     * @param inflater El inflater.
     * @param container El contenedor del fragmento.
     * @param savedInstanceState El estado previamente guardado, si lo hay.
     * @return La vista inflada del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, // Método que infla el diseño del fragmento
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);

        // Inicializar los EditText
        idText = rootView.findViewById(R.id.editTextID); // Obtener el EditText para el ID del producto
        nameText = rootView.findViewById(R.id.editTextNombre); // Obtener el EditText para el nombre del producto
        descriptionText = rootView.findViewById(R.id.editTextDescription); // Obtener el EditText para la descripcion del producto
        priceText = rootView.findViewById(R.id.editTextPrecio); // Obtener el EditText para el precio del producto
        editTextUrlImagen = rootView.findViewById(R.id.editTextUrlImagen); // Obtener el EditText para la URL de la imagen del producto

        retrofit = new Retrofit.Builder().baseUrl(HomeFragment.IPHost) // Configurar Retrofit con la URL base
                .addConverterFactory(GsonConverterFactory.create()) // Agregar el convertidor Gson
                .build(); // Construir el objeto Retrofit

        // Inicializar el botón
        button = rootView.findViewById(R.id.buttonActualizarProducto); // Obtener el botón de actualización del producto

        // Agregar un listener al botón para manejar clicks
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar(); // Llamar al método de actualización
            }
        });

        return rootView; // Devolver la vista inflada
    }

    /**
     * Método para actualizar el producto.
     */
    private void actualizar() { // Método para actualizar el producto
        String id = idText.getText().toString().trim(); // Obtener el ID del producto
        String nombre = nameText.getText().toString().trim(); // Obtener el nombre del producto
        String description = descriptionText.getText().toString().trim(); // Obtener la descripcion del producto
        String precio = priceText.getText().toString().trim(); // Obtener el precio del producto
        String imagen = editTextUrlImagen.getText().toString().trim(); // Obtener la URL de la imagen del producto

        // Crear un objeto ProductDTO en lugar de Product
        ProductDTO productDTO = new ProductDTO(nombre, description, Float.parseFloat(precio), imagen);

        crudInterface = retrofit.create(CRUDInterface.class); // Crear una instancia de CRUDInterface

        // Llamar al método actualizar con el DTO y el ID
        Call<Product> call = crudInterface.actualizar(Integer.parseInt(id), productDTO);

        call.enqueue(new Callback<Product>() { // Realizar la llamada de manera asíncrona
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) { // Manejar la respuesta
                if (!response.isSuccessful()) { // Si la respuesta no es exitosa
                    Log.e("Response err ", response.message()); // Registrar un error
                    return;
                }
                Product product = response.body(); // Obtener el producto actualizado
                // Hacer algo con el producto actualizado si es necesario
                mostrarToast("Producto actualizado: " + product.getName()); // Mostrar un mensaje emergente con el nombre del producto actualizado
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) { // Manejar el fallo de la llamada
                Log.e("Throw err:", t.getMessage()); // Registrar un error
                mostrarToast("Error al actualizar el producto"); // Mostrar un mensaje emergente de error
            }
        });
    }

    /**
     * Método para mostrar un Toast.
     * @param mensaje El mensaje que se mostrará en el Toast.
     */    private void mostrarToast(String mensaje) { // Método para mostrar un mensaje emergente
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show(); // Mostrar un Toast con el mensaje proporcionado
    }
}
