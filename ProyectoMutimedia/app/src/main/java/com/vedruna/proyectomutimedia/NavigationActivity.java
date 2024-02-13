package com.vedruna.proyectomutimedia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) { // Método onCreate con un parámetro de tipo Bundle llamado savedInstanceState
        super.onCreate(savedInstanceState); // Llama al método onCreate de la clase padre
        setContentView(R.layout.navigationmenu); // Establece el contenido de la actividad a partir del layout definido en navigationmenu.xml
        onNavigationItemSelectedListener(); // Llama al método onNavigationItemSelectedListener
    }

    protected void onNavigationItemSelectedListener(){ // Método onNavigationItemSelectedListener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView); // Declara e inicializa una instancia de BottomNavigationView buscando su ID en el layout
        bottomNavigationView.setSelectedItemId(R.id.navigation_home); // Establece el ítem seleccionado en la vista BottomNavigationView como la opción "navigation_home"

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment); // Obtiene el fragmento de navegación anidado en el layout
        NavController navController = navHostFragment.getNavController(); // Obtiene el controlador de navegación del fragmento

        bottomNavigationView.setOnItemSelectedListener(item -> { // Establece un Listener para manejar eventos de selección en la BottomNavigationView

            if (item.getItemId() == R.id.navigation_home) { // Si el ítem seleccionado es "navigation_home"
                navController.navigate(R.id.homeFragment); // Navega al fragmento llamado "homeFragment"
            } else if (item.getItemId() == R.id.navigation_create) { // Si el ítem seleccionado es "navigation_create"
                navController.navigate(R.id.createFragment); // Navega al fragmento llamado "createFragment"
            } else if (item.getItemId() == R.id.navigation_update) { // Si el ítem seleccionado es "navigation_update"
                navController.navigate(R.id.updateFragment); // Navega al fragmento llamado "updateFragment"
            }else if (item.getItemId() == R.id.navigation_delete) { // Si el ítem seleccionado es "navigation_delete"
                navController.navigate(R.id.deleteFragment); // Navega al fragmento llamado "deleteFragment"
            }else if (item.getItemId() == R.id.navigation_exit) { // Si el ítem seleccionado es "navigation_exit"
                navController.navigate(R.id.exitFragment); // Navega al fragmento llamado "exitFragment"
            }
            return true; // Devuelve verdadero para indicar que el evento de selección fue manejado
        });
    }
}
