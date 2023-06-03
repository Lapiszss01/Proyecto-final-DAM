package com.example.proyectofinaldam.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Cancion;

import java.util.ArrayList;

public class AdaptadorCanciones extends RecyclerView.Adapter<AdaptadorCanciones.ViewHolder>{

    //Arrastrar las canciones al movil y luego moverlas de la carpeta de descargas a la de musica

    ArrayList<Cancion> listaCanciones;
    Context context;
    protected LayoutInflater inflador;

    protected View.OnClickListener onClickListener;

    public AdaptadorCanciones(ArrayList<Cancion> listaCanciones, Context context) {
        this.listaCanciones = listaCanciones;
        this.context = context;

        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cancion,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Cancion dataCancion = listaCanciones.get(position);
        holder.tituloTextView.setText(dataCancion.getTitulo());
        holder.artistaTextView.setText(dataCancion.getArtista());
        holder.albumTextView.setText(dataCancion.getAlbum());



    }

    @Override
    public int getItemCount() {
        return listaCanciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tituloTextView, artistaTextView, albumTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.titulo);
            artistaTextView = itemView.findViewById(R.id.artista);
            albumTextView = itemView.findViewById(R.id.album);
        }



    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}




