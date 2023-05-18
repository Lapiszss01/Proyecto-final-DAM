package com.example.proyectofinaldam.ui.Ejercicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectofinaldam.R;


public class EjercicioFragment extends Fragment {

    private RecyclerView rvCategorias;
    //private CategoriasAdapter categoriasAdapter;
    private RecyclerView rvTasks;
    //private TasksAdapter tasksAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ejercicio, container, false);



    }




}