package com.example.proyectofinaldam.data.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Categorias;


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

        holder.tvName.setText(lista.get(position).getNombre());

        if(lista.get(position).getSelected()){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#8ECE9D"));
        } else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#5A3D3D"));
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRutinaName);
            cardView = itemView.findViewById(R.id.cvCard);
        }


    }

    @Override
    public int getItemCount() {return lista.size();}

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}