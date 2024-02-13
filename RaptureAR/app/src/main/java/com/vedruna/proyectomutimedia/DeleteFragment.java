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

import com.vedruna.proyectomutimedia.interfaz.CRUDInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para la eliminación de un producto.
 */
public class DeleteFragment extends Fragment {
    CRUDInterface crudInterface; // Declara una instancia de la interfaz CRUDInterface
    Button button; // Declara un botón
    EditText idEditText; // Declara un EditText para ingresar el ID

    /**
     * Constructor vacío requerido.
     */
    public DeleteFragment() {
    }

    /**
     * Método de creación del fragmento.
     * @param savedInstanceState Datos previamente guardados del fragmento.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Llama al método onCreate de la superclass
        super.onCreate(savedInstanceState);
    }

    /**
     * Método para crear la vista del fragmento.
     * @param inflater El LayoutInflater para inflar el layout del fragmento.
     * @param container El ViewGroup que contiene la vista del fragmento.
     * @param savedInstanceState Datos previamente guardados del fragmento.
     * @return La vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        // Inicializar el EditText para el ID
        idEditText = view.findViewById(R.id.editTextID);
        // Configurar el botón de borrado con el click
        setupDeleteButton(view);
        return view;
    }

    /**
     * Método para configurar el botón de borrado.
     * @param view La vista que contiene el botón.
     */
    private void setupDeleteButton(View view) {
        button = view.findViewById(R.id.buttonBorrarProducto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el ID ingresado por el usuario
                String idString = idEditText.getText().toString().trim();
                // Verifica si el ID no está vacío
                if (!idString.isEmpty()) {
                    int productId = Integer.parseInt(idString);
                    delete(productId);
                } else {
                    // Manejar el caso en el que el ID esté vacío
                    Log.e("Error", "El ID no puede estar vacío");
                }
            }
        });
    }

    /**
     * Método para borrar un producto.
     * @param id El ID del producto a borrar.
     */
    private void delete(int id) {
        // Construir la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeFragment.IPHost)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Crear la interfaz CRUDInterface
        crudInterface = retrofit.create(CRUDInterface.class);
        // Llamar al método de borrado con el ID del producto
        Call<Void> call = crudInterface.delete(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err ", response.message());
                    return;
                }
                mostrarToast("Producto eliminado");
                // Borrado exitoso, puedes realizar acciones adicionales si es necesario
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
            }
        });
    }

    /**
     * Método para mostrar un Toast.
     * @param mensaje El mensaje a mostrar en el Toast.
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
