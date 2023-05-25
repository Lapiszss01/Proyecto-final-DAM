package com.example.proyectofinaldam.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.ui.Ejercicio.EjercicioCatViewHolder;

import java.util.ArrayList;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.ViewHolder> {

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
    public CategoriasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categoria,parent,false);
        view.setOnClickListener(onClickListener);
        return new CategoriasAdapter.ViewHolder(view);
    }



    public void onBindViewHolder(CategoriasAdapter.ViewHolder holder, int position) {

        Categorias lista2 = lista.get(position);
        holder.tvName.setText(lista.get(position).getNombre());

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRutinaName);
        }


    }

    @Override
    public int getItemCount() {return lista.size();}
}