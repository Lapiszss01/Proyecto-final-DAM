package com.example.proyectofinaldam.ui.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.DatosUsuario;
import com.example.proyectofinaldam.ui.Login.LoginActivity;
import com.google.android.material.slider.RangeSlider;

public class EditarPerfilActivity extends AppCompatActivity implements View.OnClickListener {

    public String usuario,pass,nombre,ap,email;
    public int id,genero,peso2,edad2;

    public int altura = 120;
    public int actividadF = -1;

    public RadioButton rb0,rb1,rb2,rb3;
    public RadioGroup rg0,rg1;

    public TextView tvRs, tvAf;
    public EditText peso,edad;
    public RangeSlider rs;
    Button btnRegistro;
    DatosUsuario dao;
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        peso = (EditText) findViewById(R.id.etWeight);
        edad = (EditText) findViewById(R.id.etAge);
        rs = (RangeSlider) findViewById(R.id.rsHeight);
        rs.setOnClickListener(this);
        btnRegistro = (Button) findViewById(R.id.btnRegister);
        btnRegistro.setOnClickListener(this);
        tvRs = (TextView) findViewById(R.id.tvHeight);
        tvAf = (TextView) findViewById(R.id.tvActividad);

        rg0 = (RadioGroup) findViewById(R.id.rg0);
        rg1 = (RadioGroup) findViewById(R.id.rg1);

        rb0 = (RadioButton) findViewById(R.id.radioButton0);
        rb0.setOnClickListener(this);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb1.setOnClickListener(this);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb2.setOnClickListener(this);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb3.setOnClickListener(this);
        dao = new DatosUsuario(this);
        rs.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                altura = Math.round(value);
                tvRs.setText(Integer.toString(altura));
            }
        });

        //Pilla los datos de la vista anterior
        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        usuario = b.getString("usuario");
        pass = b.getString("pass");
        nombre = b.getString("nombre");
        ap = b.getString("ap");
        email = b.getString("email");
        genero = b.getInt("genero");

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnRegister:
                Usuario u = new Usuario();


                u.setId(id);
                u.setUsuario(usuario);
                u.setPassword(pass);
                u.setNombre(nombre);
                u.setApellidos(ap);
                u.setEmail(email);
                u.setGenero(genero);

                u.setAltura(altura);
                if(!peso.getText().toString().equals("")){u.setPeso(Integer.parseInt(peso.getText().toString()));}
                if(!edad.getText().toString().equals("")){u.setEdad(Integer.parseInt(edad.getText().toString()));}
                u.setActividadF(actividadF);

                Log.d("Usuario", u.toString());

                if(!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                } else if(dao.updateUsuario(u)) {
                    Intent i = new Intent(EditarPerfilActivity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Actualizacion Exitosa",Toast.LENGTH_LONG).show();
                    finish();
                } else{
                    Toast.makeText(this,"No se puede actualizar",Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.radioButton0:
                rg1.clearCheck();
                actividadF = 0;
                tvAf.setText("Nada o poco de ejercicio");
                break;
            case R.id.radioButton1:
                rg1.clearCheck();
                actividadF = 1;
                tvAf.setText("Ejercicio 2-3 días por semana");
                break;
            case R.id.radioButton2:
                rg0.clearCheck();
                actividadF = 2;
                tvAf.setText("Ejercicio 4-5 días por semana");
                break;
            case R.id.radioButton3:
                rg0.clearCheck();
                actividadF = 3;
                tvAf.setText("Ejercicio 6-7 días por semana");
                break;

        }

    }
}
