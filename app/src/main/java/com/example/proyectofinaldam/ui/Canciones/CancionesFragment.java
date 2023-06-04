package com.example.proyectofinaldam.ui.Canciones;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.MyMediaPlayer;
import com.example.proyectofinaldam.data.adapter.AdaptadorCanciones;
import com.example.proyectofinaldam.data.model.Cancion;
import com.example.proyectofinaldam.ui.MainHubActivity;

import java.util.ArrayList;

public class CancionesFragment extends Fragment {

    TextView sinCanciones, pideReinicio;
    private RecyclerView recyclerView;
    public AdaptadorCanciones adaptador;

    ArrayList<Cancion> listaCanciones = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_musica, container, false);

        super.onCreate(savedInstanceState);


        sinCanciones = view.findViewById(R.id.sinCanciones);
        pideReinicio = view.findViewById(R.id.pideReinicio);
        pideReinicio.setVisibility(View.GONE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);



        String[] projection = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM

        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);

        while (cursor.moveToNext()) {
            Cancion dataCancion = new Cancion(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3));
            listaCanciones.add(dataCancion);

        }
        if (listaCanciones.size() == 0) {
            sinCanciones.setVisibility(View.VISIBLE);

        }else{
            //Recycler view

            adaptador = new AdaptadorCanciones(listaCanciones,getContext());
            recyclerView.setAdapter(adaptador);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            adaptador.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildAdapterPosition(v);


                    MyMediaPlayer.getInstance().reset();
                    MyMediaPlayer.currentIndex = position;
                    Intent intent = new Intent(getActivity(),MediaPlayerActivity.class);

                    intent.putExtra("LIST",listaCanciones);
                    intent.putExtra("pos", position);
                    intent.putExtra("titulo", listaCanciones.get(position).getTitulo());
                    intent.putExtra("path", listaCanciones.get(position).getPath());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

        }
        return view;
    }





}