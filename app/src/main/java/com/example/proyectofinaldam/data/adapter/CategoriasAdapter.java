package com.example.proyectofinaldam.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.ui.Ejercicio.EjercicioCatViewHolder;

import java.util.ArrayList;

public class CategoriasAdapter extends RecyclerView.Adapter<EjercicioCatViewHolder> {

    ArrayList<Categorias> lista;
    Context context;
    protected LayoutInflater inflador;

    protected View.OnClickListener onClickListener;

    public CategoriasAdapter(ArrayList<Categorias> lista, Context context) {

        this.lista = lista;
        this.context = context;

        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @NonNull
    @Override
    public EjercicioCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categoria,parent,false);
        view.setOnClickListener(onClickListener);
        return new EjercicioCatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioCatViewHolder holder, int position) {

        Categorias lista2 = lista.get(position);
        /*holder.tituloTextView.setText(dataCancion.getTitulo());
        holder.artistaTextView.setText(dataCancion.getArtista());
        holder.albumTextView.setText(dataCancion.getAlbum());*/

    }

    @Override
    public int getItemCount() {return lista.size();}
}