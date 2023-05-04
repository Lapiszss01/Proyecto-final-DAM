package com.example.proyectofinaldam.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.DatosUsuario;

public class MainHubActivity extends AppCompatActivity {

    int id = 0;
    Usuario u;
    DatosUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new DatosUsuario(this);
        u = dao.getUsuarioById(id);

        Log.d("Funciona?",""+u.toString());

    }

}
