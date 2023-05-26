package com.example.proyectofinaldam.ui.Ejercicio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.adapter.CategoriasAdapter;
import com.example.proyectofinaldam.data.adapter.EjerciciosAdapter;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;

import java.util.ArrayList;
import java.util.List;


public class EjercicioFragment extends Fragment {


    ArrayList<Categorias> lista = new ArrayList<>();
    ArrayList<Ejercicios> lista2 = new ArrayList<>();

    private RecyclerView rvCategorias;
    private CategoriasAdapter categoriasAdapter;

    private RecyclerView rvEjercicios;
    private EjerciciosAdapter ejerciciosAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicio, container, false);

        //Crea las listas que van a ser básicamente una los ejercicios y otra la parte del cuerpo que ejercitan
        Categorias Cat1 = new Categorias(true,0,"Full Body");
        Categorias Cat2 = new Categorias(true,1,"Piernas");
        Categorias Cat3 = new Categorias(true,2,"Pecho");
        lista.add(Cat1);
        lista.add(Cat2);
        lista.add(Cat3);

        Ejercicios Ej1 = new Ejercicios("Peso",Cat1);
        Ejercicios Ej2 = new Ejercicios("Peso2",Cat1);
        Ejercicios Ej3 = new Ejercicios("Peso3",Cat2);
        lista2.add(Ej1);
        lista2.add(Ej2);
        lista2.add(Ej3);
        lista2.add(Ej1);
        lista2.add(Ej2);
        lista2.add(Ej3);

        rvCategorias = view.findViewById(R.id.rvCategories);
        rvCategorias.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoriasAdapter = new CategoriasAdapter(lista, requireContext());
        rvCategorias.setAdapter(categoriasAdapter);
        rvCategorias.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));

        //OnClick Position de las canciones

        rvEjercicios = view.findViewById(R.id.rvTasks);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(getActivity()));
        ejerciciosAdapter = new EjerciciosAdapter(lista2,requireContext());
        rvEjercicios.setAdapter(ejerciciosAdapter);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));

        return view;
    }

    private void onItemSelected(int position){
        boolean seleccionado = lista2.get(position).getSelected();
        lista2.get(position).setSelected(!seleccionado);
        updateTask();
    }

    private void updateCategories(int position){
        boolean seleccionado = lista.get(position).getSelected();
        lista.get(position).setSelected(!seleccionado);
        updateTask();
    }

    private void updateTask(){
        /*ArrayList<Categorias> selectedCategories = null;
        for (Categorias cat : lista){
            if(cat.getSelected()){selectedCategories.add(cat);}
        }
        ArrayList<Ejercicios> nuevosEjs = null;
        for(Ejercicios ejs : lista2){
            for(int i = 0; i<selectedCategories.size(); i++){
                if(ejs.getCategoria() == selectedCategories.get(i)){
                    nuevosEjs.add(ejs);
                }
            }
        }

        ejerciciosAdapter.notifyDataSetChanged();*/

    }

    /*private fun updateTask(){
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks

        tasksAdapter.notifyDataSetChanged()
    }*/


}