package com.vedruna.proyectomutimedia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

/**
 * La clase ExitFragment es un Fragmento que permite al usuario salir de la aplicación.
 */
public class ExitFragment extends Fragment {
    FirebaseAuth firebaseAuth; // Declaración de la instancia firebaseAuth de la clase FirebaseAuth

    /**
     * Constructor público vacío requerido.
     */    public ExitFragment() {
    }

    /**
     * Método llamado cuando se crea el Fragmento.
     * @param savedInstanceState Objeto Bundle que contiene el estado anteriormente guardado del Fragmento.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método llamado para crear y devolver la vista asociada al Fragmento.
     * @param inflater El objeto LayoutInflater que se utiliza para inflar cualquier vista en el Fragmento.
     * @param container El ViewGroup al que se adjuntará la vista inflada.
     * @param savedInstanceState Objeto Bundle que contiene el estado anteriormente guardado del Fragmento.
     * @return La vista inflada asociada al Fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_exit, container, false);

        // Aquí puedes agregar cualquier lógica adicional que necesites para el fragmento de salida
        firebaseAuth=FirebaseAuth.getInstance();
        // Cuando el usuario hace click en el botón de salida, cierra la aplicación
        view.findViewById(R.id.btnExit).setOnClickListener(new View.OnClickListener() {
            // Llamar al método logOut() cuando el usuario hace click en el botón de salida
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        return view;
    }

    /**
     * Método para cerrar la sesión del usuario actual.
     */
    private void logOut() {
        // Cerrar sesión del usuario actual utilizando FirebaseAuth
        firebaseAuth.signOut();
        // Llamar al método backToLogin() para volver a la actividad de inicio de sesión
        backToLogin();
    }

    /**
     * Método para volver a la actividad de inicio de sesión.
     */
    private void backToLogin(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}