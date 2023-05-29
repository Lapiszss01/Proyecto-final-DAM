package com.example.proyectofinaldam.ui.Login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.adapter.CategoriasAdapter;
import com.example.proyectofinaldam.data.adapter.EjerciciosAdapter;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;
import com.example.proyectofinaldam.data.model.Usuario;

import java.util.ArrayList;

public class PerfilFragment extends Fragment {

    Usuario u;
    TextView tvUser, tvEmail, tvWelcome;

    public PerfilFragment(Usuario u){
        this.u = u;
    }

    //Nada por ahora

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        tvWelcome = view.findViewById(R.id.TV_bienvenida);
        tvWelcome.setText("aaa");
        tvUser = view.findViewById(R.id.TV_user);
        tvEmail = view.findViewById(R.id.TV_email);
        return view;

    }
}