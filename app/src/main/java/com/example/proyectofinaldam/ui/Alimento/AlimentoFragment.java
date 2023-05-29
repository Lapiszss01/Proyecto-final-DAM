package com.example.proyectofinaldam.ui.Alimento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;

public class AlimentoFragment extends Fragment {

    Usuario u;

    public AlimentoFragment(Usuario u){
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alimento, container, false);
    }
}