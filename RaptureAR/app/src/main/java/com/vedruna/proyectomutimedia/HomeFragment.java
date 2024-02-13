package com.vedruna.proyectomutimedia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vedruna.proyectomutimedia.adapters.ProductAdapter;
import com.vedruna.proyectomutimedia.interfaz.CRUDInterface;
import com.vedruna.proyectomutimedia.model.Product;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para la pantalla principal (Home).
 */
public class HomeFragment extends Fragment {
    // Declaración de variables y constante
    List<Product> productList;
    CRUDInterface crudInterface;
    ListView listView;
    public static String IPHost = "http://192.168.0.18:8080";

    /** Constructor vacío requerido por la clase Fragment. */
    public HomeFragment() {
    }

    /**
     * @param savedInstanceState Si el fragmento se está recreando a partir de
     * un estado guardado anterior, este es el estado.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método para obtener todos los productos del servidor.
     */
    private void gelAll(){
        // Método para obtener todos los productos
        Retrofit retrofit=new Retrofit.Builder().baseUrl(IPHost).
                addConverterFactory(GsonConverterFactory.create()).build();
        // Crea una instancia de Retrofit para realizar llamadas a la API
        crudInterface=retrofit.create(CRUDInterface.class);
        // Crea una instancia de la interfaz CRUDInterface
        Call<List<Product>> call=crudInterface.getAll();
        // Realiza una llamada para obtener todos los productos
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                // Se ejecuta cuando la llamada es exitosa
                if(!response.isSuccessful()){
                    Log.e("Response err ",response.message());
                    return;
                }
                // Si la respuesta no es exitosa, registra un error en el registro de eventos
                productList=response.body();
                // Obtiene la lista de productos de la respuesta
                ProductAdapter productAdapter = new ProductAdapter(requireContext(), productList);
                // Crea un adaptador de productos
                listView.setAdapter(productAdapter);
                // Establece el adaptador en la vista de lista
                productList.forEach(p-> Log.i("Productos: ",p.toString()));
                // Registra cada producto en el registro de eventos
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Se ejecuta cuando la llamada falla
                Log.e("Throw err:", Objects.requireNonNull(t.getMessage()));
                // Registra el error en el registro de eventos
            }
        });
    }

    /**
     *
     * @param inflater El objeto LayoutInflater que se puede utilizar para inflar
     * cualquier vista en el fragmento,
     * Contenedor @param Si no es nulo, esta es la vista principal en la que se encuentra el fragmento.
     * Se debe adjuntar la interfaz de usuario. El fragmento no debe agregar la vista en sí,
     * pero esto se puede usar para generar los LayoutParams de la vista.
     * @param saveInstanceState Si no es nulo, este fragmento se está reconstruyendo
     * de un estado guardado anterior como se indica aquí.
     *
     * @return Devuelve la vista creada
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se llama cuando se crea la vista del fragmento
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Infla el diseño de la interfaz de usuario del fragmento
        listView = view.findViewById(R.id.listView);
        // Busca la vista de lista en el diseño de la interfaz de usuario

        gelAll();
        // Llama al método para obtener todos los productos
        return view;
        // Devuelve la vista creada
    }
}