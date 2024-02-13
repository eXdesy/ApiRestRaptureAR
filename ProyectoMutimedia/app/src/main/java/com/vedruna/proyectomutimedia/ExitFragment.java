package com.vedruna.proyectomutimedia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

public class ExitFragment extends Fragment {
    FirebaseAuth firebaseAuth; // Declaración de la instancia firebaseAuth de la clase FirebaseAuth

    // Constructor público vacío requerido
    public ExitFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    private void logOut() {
        // Cerrar sesión del usuario actual utilizando FirebaseAuth
        firebaseAuth.signOut();
        // Llamar al método backToLogin() para volver a la actividad de inicio de sesión
        backToLogin();
    }

    // Método para volver a la actividad de inicio de sesión
    private void backToLogin(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}