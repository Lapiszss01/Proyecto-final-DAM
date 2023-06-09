package com.example.proyectofinaldam.ui.Ejercicio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.adapter.CategoriasAdapter;
import com.example.proyectofinaldam.data.adapter.EjerciciosAdapter;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;
import com.example.proyectofinaldam.data.model.Usuario;

import java.util.ArrayList;


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

        if (checkPermission() == false) {
            requestPermission();
        }

        return view;

    }

    private void rellenaListas() {

        //Crea las listas que van a ser básicamente una los ejercicios y otra la parte del cuerpo que ejercitan
        Categorias catFullBody = new Categorias(true,0,"Full Body");
        lista.add(catFullBody);

        Categorias catPechoEspalda = new Categorias(false,1,"Pecho y espalda");
        lista.add(catPechoEspalda);

        Categorias catPierna = new Categorias(false,2,"Pierna");
        lista.add(catPierna);

        Categorias catHombro = new Categorias(false,3,"Hombro");
        lista.add(catHombro);

        Ejercicios Ej1 = new Ejercicios("Prensa Pendular",catFullBody,R.raw.prensapendular,10,R.raw.prensavid,catPierna);
        Ejercicios Ej2 = new Ejercicios("Flyes/Aperturas",catFullBody,R.raw.flyes,10,R.raw.flyesvid,catPechoEspalda);
        Ejercicios Ej3 = new Ejercicios("Press militar",catFullBody,R.raw.pressmilitar,10,R.raw.pressvid,catHombro);
        Ejercicios Ej4 = new Ejercicios("Remo",catFullBody,R.raw.remo,10,R.raw.removid,catPechoEspalda);
        Ejercicios Ej5 = new Ejercicios("Curl de biceps con máquina",catFullBody,R.raw.curl,10,R.raw.curlvid,catHombro);
        Ejercicios Ej6 = new Ejercicios("Extensión de triceps con cuerda",catFullBody,R.raw.extensiontriceps,10,R.raw.extensionesvid,catHombro);

        lista2.add(Ej1);
        lista2.add(Ej2);
        lista2.add(Ej3);
        lista2.add(Ej4);
        lista2.add(Ej5);
        lista2.add(Ej6);

    }

    private ArrayList<Ejercicios> newList(int type){

        ArrayList<Ejercicios> nuevosEjs = new ArrayList<>();

        if(type == 0){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }else if(ejs.getSegCategoria() != null){
                    if(ejs.getSegCategoria().getType() == type){nuevosEjs.add(ejs);}
                }
            }
        } else if(type == 1){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }else if(ejs.getSegCategoria() != null){
                    if(ejs.getSegCategoria().getType() == type){nuevosEjs.add(ejs);}
                }
            }
        }
        else if(type == 2){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }else if(ejs.getSegCategoria() != null){
                    if(ejs.getSegCategoria().getType() == type){nuevosEjs.add(ejs);}
                }
            }
        }
        else if(type == 3){
            for(Ejercicios ejs : lista2){
                if(ejs.getCategoria().getType() == type){
                    nuevosEjs.add(ejs);
                }else if(ejs.getSegCategoria() != null){
                    if(ejs.getSegCategoria().getType() == type){nuevosEjs.add(ejs);}
                }
            }
        }

        return nuevosEjs;
    }

    boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{

            return false;
        }
    }

    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(getContext(),"Permisos de lectura requeridos, activelos en la configuracion",Toast.LENGTH_LONG).show();
        }else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
            //Toast.makeText(getContext(),"Reinicie la aplicación después de permitir uso para poder utilizarla",Toast.LENGTH_LONG).show();
            //Toast.makeText(getContext(),"AAAA",Toast.LENGTH_LONG).show();


        }
    }



}