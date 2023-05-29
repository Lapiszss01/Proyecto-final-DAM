package com.example.proyectofinaldam.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;

import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.ViewHolder>{

    //Todos los adapters hacen que se vea la lista en la app

    public ArrayList<Ejercicios> lista;
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
    public EjerciciosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ejercicio,parent,false);
        view.setOnClickListener(onClickListener);
        return new EjerciciosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(lista.get(position).getNombre());
        holder.iV.setImageResource(lista.get(position).getFoto());


        holder.tvRepeticiones.setText(lista.get(position).getRepeticiones() + " repeticiones");
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName, tvRepeticiones;
        public ImageView iV;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvRutinaName);
            tvRepeticiones = itemView.findViewById(R.id.tvRepeticiones);
            cardView = itemView.findViewById(R.id.cvCard);
            iV = itemView.findViewById(R.id.iV);
        }


    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
