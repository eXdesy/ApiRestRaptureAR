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
 * Un {@link Fragment} simple para la creación de un nuevo producto.
 */
public class CreateFragment extends Fragment {
    EditText nameText; // Declaración del EditText para el nombre
    EditText descriptionText; // Campo de texto para la descripcion del producto
    EditText priceText; // Declaración del EditText para el precio
    EditText editTextUrlImagen; // Declaración del EditText para la URL de la imagen
    Button button; // Declaración del botón
    CRUDInterface crudInterface; // Declaración de la interfaz CRUDInterface

    /**
     * Constructor público vacío requerido.
     */
    public CreateFragment() {
    }

    /**
     * Llamada al método onCreate de la clase padre.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método para crear la vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        // Inicializar los EditText
        nameText = rootView.findViewById(R.id.editTextNombre);
        descriptionText = rootView.findViewById(R.id.editTextDescription);
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
                // Obtener el texto del EditText del description
                String description = descriptionText.getText().toString();
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
                ProductDTO dto = new ProductDTO(nombre, description, precio, urlImagen);
                // Llamar al método create con el objeto ProductDTO
                create(dto);
            }
        });
        // Retornar la vista inflada
        return rootView;
    }

    /**
     * Método para crear un producto.
     */
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

    /**
     * Método para mostrar un Toast con un mensaje.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}