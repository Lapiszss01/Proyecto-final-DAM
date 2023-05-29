package com.example.proyectofinaldam.ui.Ejercicio;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

    private int tipoRuntina;
    ArrayList<Categorias> lista = new ArrayList<>();
    ArrayList<Ejercicios> lista2 = new ArrayList<>();

    private RecyclerView rvCategorias;
    private CategoriasAdapter categoriasAdapter;

    private RecyclerView rvEjercicios;
    private EjerciciosAdapter ejerciciosAdapter;

    protected View.OnClickListener onClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicio, container, false);

        rellenaListas();

        rvCategorias = view.findViewById(R.id.rvCategories);
        rvCategorias.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoriasAdapter = new CategoriasAdapter(lista, requireContext());
        rvCategorias.setAdapter(categoriasAdapter);
        rvCategorias.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));

        //OnClick Position de las canciones
        categoriasAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = rvCategorias.getChildAdapterPosition(v);
                tipoRuntina = lista.get(position).getType();

                for(Categorias cat : lista){
                    if(cat.getType() == position){
                        cat.setSelected(true);
                    }else{
                        cat.setSelected(false);
                    }
                }

                ArrayList<Ejercicios> nuevaLista = newList(tipoRuntina);
                ejerciciosAdapter.lista = nuevaLista;
                categoriasAdapter.notifyDataSetChanged();
                ejerciciosAdapter.notifyDataSetChanged();
            }
        });

        rvEjercicios = view.findViewById(R.id.rvTasks);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(getActivity()));
        ejerciciosAdapter = new EjerciciosAdapter(lista2,requireContext());
        rvEjercicios.setAdapter(ejerciciosAdapter);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        return view;

    }

    private void rellenaListas() {

        //Crea las listas que van a ser básicamente una los ejercicios y otra la parte del cuerpo que ejercitan
        Categorias Cat1 = new Categorias(false,0,"Todos los ejercicios");
        Categorias Cat2 = new Categorias(false,1,"Piernas");
        Categorias Cat3 = new Categorias(false,2,"Pecho");
        Categorias Cat4 = new Categorias(false,2,"Pecho");
        lista.add(Cat1);
        lista.add(Cat2);
        lista.add(Cat3);
        lista.add(Cat4);

        //00
        Ejercicios Ej1 = new Ejercicios("Prensa Pendular",Cat1,R.drawable.large);
        Ejercicios Ej2 = new Ejercicios("Flyes/Aperturas",Cat1,R.drawable.large);
        Ejercicios Ej3 = new Ejercicios("Press militar",Cat1,R.drawable.large);
        Ejercicios Ej4 = new Ejercicios("Remo",Cat1,R.drawable.large);
        Ejercicios Ej5 = new Ejercicios("Curl de biceps con máquina",Cat1,R.drawable.large);
        Ejercicios Ej6 = new Ejercicios("Extensión de triceps con cuerda",Cat1,R.drawable.large);

        //01
        Ejercicios Ej7 = new Ejercicios("Prensa Pendular",Cat2,R.drawable.large);

        //02
        Ejercicios Ej8 = new Ejercicios("Flyes/Aperturas",Cat3,R.drawable.large);
        Ejercicios Ej9 = new Ejercicios("Press militar",Cat3,R.drawable.large);
        Ejercicios Ej10 = new Ejercicios("Remo",Cat3,R.drawable.large);


        lista2.add(Ej1);
        lista2.add(Ej2);
        lista2.add(Ej3);
        lista2.add(Ej4);
        lista2.add(Ej5);
        lista2.add(Ej6);
        lista2.add(Ej7);
        lista2.add(Ej8);
        lista2.add(Ej9);
        lista2.add(Ej10);


        /*RUTINA FULL BODY
                -Prensa Pendular(ejercicio de pierna).
                -Flyes/Aperturas(ejercicio de pecho).
                -Press militar(ejercicio de hombro).
                -Remo(ejercicio de espalda).
                -Curl de biceps con máquina.
        -Extensión de triceps con cuerda.*/

    }

    private ArrayList<Ejercicios> newList(int type){

        ArrayList<Ejercicios> nuevosEjs = new ArrayList<>();

        if(type == 0){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }
            }
        } else if(type == 1){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }
            }
        }

        return nuevosEjs;
    }



}