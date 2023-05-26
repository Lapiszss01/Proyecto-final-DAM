package com.example.proyectofinaldam.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;
import com.example.proyectofinaldam.ui.Ejercicio.EjercicioCatViewHolder;
import com.example.proyectofinaldam.ui.Ejercicio.EjercicioViewHolder;

import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjercicioViewHolder>{

    //Todos los adapters hacen que se vea la lista en la app

    ArrayList<Ejercicios> lista;
    Context context;
    protected LayoutInflater inflador;

    protected View.OnClickListener onClickListener;

    public EjerciciosAdapter(ArrayList<Ejercicios> lista,Context context) {

        this.context = context;
        this.lista = lista;

        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ejercicio,parent,false);
        view.setOnClickListener(onClickListener);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
