package com.example.proyectofinaldam.ui.Ejercicio;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.proyectofinaldam.MainActivity;
import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.adapter.CategoriasAdapter;
import com.example.proyectofinaldam.data.adapter.EjerciciosAdapter;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;
import com.example.proyectofinaldam.data.model.Usuario;

import java.util.ArrayList;
import java.util.List;


public class EjercicioFragment extends Fragment {

    public EjercicioFragment(Usuario u, String packageName){
        this.u=u;
        this.packageName = packageName;
    }

    private int tipoRuntina;
    Usuario u;
    String packageName;
    ArrayList<Categorias> lista = new ArrayList<>();
    ArrayList<Ejercicios> lista2 = new ArrayList<>();
    ArrayList<Ejercicios> nuevaLista = new ArrayList<>();
    private RecyclerView rvCategorias;
    private CategoriasAdapter categoriasAdapter;

    private RecyclerView rvEjercicios;
    private EjerciciosAdapter ejerciciosAdapter;

    protected View.OnClickListener onClickListener;
    private VideoView videoView;

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

                nuevaLista = newList(tipoRuntina);
                ejerciciosAdapter.lista = nuevaLista;
                categoriasAdapter.notifyDataSetChanged();
                ejerciciosAdapter.notifyDataSetChanged();
            }
        });

        rvEjercicios = view.findViewById(R.id.rvTasks);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(getActivity()));
        nuevaLista = lista2;
        ejerciciosAdapter = new EjerciciosAdapter(lista2,requireContext());

        ejerciciosAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = rvCategorias.getChildAdapterPosition(v);
                Ejercicios ej = nuevaLista.get(position);
                Log.d("Posicion", ej.getNombre());

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mview = getLayoutInflater().inflate(R.layout.dialog_video,null);
                Log.d("Posicion", packageName + " " + R.raw.a);

                //TODO que el r.raw.a sea un parametro idVideo del modal
                videoView = (VideoView) mview.findViewById(R.id.videoView);
                videoView.setVideoPath("android.resource://com.example.proyectofinaldam/" + ej.getVideo());

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                        videoView.start();
                    }
                });
                mBuilder.setView(mview);
                AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        });

        rvEjercicios.setAdapter(ejerciciosAdapter);
        rvEjercicios.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        return view;

    }

    private void rellenaListas() {

        //Crea las listas que van a ser básicamente una los ejercicios y otra la parte del cuerpo que ejercitan
        Categorias Cat1 = new Categorias(true,0,"Full Body");
        Categorias Cat2 = new Categorias(false,1,"Piernas");
        Categorias Cat3 = new Categorias(false,2,"Pecho");
        Categorias Cat4 = new Categorias(false,3,"Am");
        lista.add(Cat1);
        lista.add(Cat2);
        lista.add(Cat3);
        lista.add(Cat4);

        //00
        Ejercicios Ej1 = new Ejercicios("Prensa Pendular",Cat1,R.raw.large,10,R.raw.a);
        Ejercicios Ej2 = new Ejercicios("Flyes/Aperturas",Cat1,R.raw.large,10,R.raw.a);
        Ejercicios Ej3 = new Ejercicios("Press militar",Cat1,R.raw.large,10,R.raw.a);
        Ejercicios Ej4 = new Ejercicios("Remo",Cat1,R.raw.large,10,R.raw.a);
        Ejercicios Ej5 = new Ejercicios("Curl de biceps con máquina",Cat1,R.raw.large,10,R.raw.a);
        Ejercicios Ej6 = new Ejercicios("Extensión de triceps con cuerda",Cat1,R.raw.large,10,R.raw.a);

        //01
        Ejercicios Ej7 = new Ejercicios("Prensa Pendular",Cat2,R.raw.large,10,R.raw.a);

        //02
        Ejercicios Ej8 = new Ejercicios("Flyes/Aperturas",Cat3,R.raw.large,10,R.raw.a);
        Ejercicios Ej9 = new Ejercicios("Press militar",Cat3,R.raw.large,10,R.raw.a);
        Ejercicios Ej10 = new Ejercicios("Remo",Cat3,R.raw.large,10,R.raw.a);


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
        else if(type == 2){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }
            }
        }

        return nuevosEjs;
    }



}