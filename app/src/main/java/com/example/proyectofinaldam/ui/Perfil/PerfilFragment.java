package com.example.proyectofinaldam.ui.Perfil;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.adapter.CategoriasAdapter;
import com.example.proyectofinaldam.data.adapter.EjerciciosAdapter;
import com.example.proyectofinaldam.data.model.Categorias;
import com.example.proyectofinaldam.data.model.Ejercicios;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.ui.Alimento.AlimentoGanarMusculoActivity;
import com.example.proyectofinaldam.ui.Alimento.AlimentoMantenimientoActivity;
import com.example.proyectofinaldam.ui.Alimento.AlimentoPerderGrasaActivity;
import com.example.proyectofinaldam.ui.Login.LoginActivity;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements View.OnClickListener {

    Usuario u;
    TextView tvUser, tvEmail, tvWelcome, tvAltura, tvEdad, tvPeso, tvActividadFisica;
    Button btnCerrarSesion, btnEditar;

    public int sexo = 0;
    public PerfilFragment(Usuario u){
        this.u = u;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        tvWelcome = view.findViewById(R.id.TV_bienvenida);
        btnCerrarSesion = view.findViewById(R.id.BTN_logout);
        btnCerrarSesion.setOnClickListener(this);

        btnEditar = view.findViewById(R.id.btnEdit);
        btnEditar.setOnClickListener(this);

        if(u.getGenero()==0){tvWelcome.setText("Bienvenido");}
        else {tvWelcome.setText("Bienvenida");}

        tvUser = view.findViewById(R.id.TV_user);
        tvUser.setText(u.getNombre() + " " +u.getApellidos());

        tvEmail = view.findViewById(R.id.TV_email);
        tvEmail.setText(u.getEmail());

        tvAltura = view.findViewById(R.id.tvAltura);
        tvAltura.setText(Integer.toString(u.getAltura())+" cm");

        tvEdad = view.findViewById(R.id.tvAEdad);
        tvEdad.setText(Integer.toString(u.getEdad()));

        tvPeso = view.findViewById(R.id.tvPeso);
        tvPeso.setText(Integer.toString(u.getPeso())+" kg");

        tvActividadFisica = view.findViewById(R.id.tvActFisica);
        if(u.getActividadF()==0){tvActividadFisica.setText("Sedentario");}
        else if(u.getActividadF()==1){tvActividadFisica.setText("Ligero");}
        else if(u.getActividadF()==2){tvActividadFisica.setText("Moderado");}
        else if(u.getActividadF()==3){tvActividadFisica.setText("Alto");}


        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.BTN_logout:
                Intent i2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(i2);
                break;
            case R.id.btnEdit:
                Intent i3 = new Intent(getActivity(), EditarPerfilActivity.class);
                i3.putExtra("Id",u.getId());
                i3.putExtra("usuario",u.getUsuario());
                i3.putExtra("pass",u.getPassword());
                i3.putExtra("nombre",u.getNombre());
                i3.putExtra("ap",u.getApellidos());
                i3.putExtra("email",u.getEmail());
                i3.putExtra("genero",u.getGenero());
                startActivity(i3);
                break;
        }

    }
}