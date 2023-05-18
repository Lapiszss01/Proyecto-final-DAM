package com.example.proyectofinaldam.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.DatosUsuario;
import com.example.proyectofinaldam.databinding.ActivityMainHubBinding;
import com.example.proyectofinaldam.ui.Alimento.AlimentoFragment;
import com.example.proyectofinaldam.ui.Ejercicio.EjercicioFragment;
import com.example.proyectofinaldam.ui.Login.PerfilFragment;

public class MainHubActivity extends AppCompatActivity {

    @NonNull ActivityMainHubBinding binding;

    int id = 0;
    Usuario u;
    DatosUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new EjercicioFragment());

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new DatosUsuario(this);
        u = dao.getUsuarioById(id);
        Log.d("Funciona?",""+u.toString());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.menuEj:
                    replaceFragment(new EjercicioFragment());
                    break;
                case R.id.menuAlim:
                    replaceFragment(new AlimentoFragment());
                    break;
                case R.id.menuPerf:
                    replaceFragment(new PerfilFragment());
                    break;
                case R.id.menuPref:
                    replaceFragment(new ConfiguracionFragment());
                    break;

            }

            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}
