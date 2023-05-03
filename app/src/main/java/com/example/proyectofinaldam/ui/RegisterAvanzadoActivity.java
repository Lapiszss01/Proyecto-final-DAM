package com.example.proyectofinaldam.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.proyectofinaldam.MainActivity;
import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.datosUsuario;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.RangeSlider;

public class RegisterAvanzadoActivity extends AppCompatActivity implements View.OnClickListener {

    public String nombre,apellido,usuario,email,contraseña;
    public boolean mascSeleccionado = true;
    public boolean femSeleccionado = false;

    public EditText peso,edad;
    public RangeSlider rs;
    public CardView cvMale, cvFemale;
    Button btnRegistro;
    datosUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_1);

        peso = (EditText) findViewById(R.id.etWeight);
        edad = (EditText) findViewById(R.id.etAge);
        rs = (RangeSlider) findViewById(R.id.rsHeight);
        cvMale = (MaterialCardView) findViewById(R.id.cvMasc);
        cvMale.setOnClickListener(this);
        cvFemale = (MaterialCardView) findViewById(R.id.cvFem);
        cvFemale.setOnClickListener(this);
        btnRegistro = (Button) findViewById(R.id.btnRegister);
        btnRegistro.setOnClickListener(this);
        dao = new datosUsuario(this);

        /*
        Bundle b = getIntent().getExtras();
        nombre = b.getString("Nombre");
        apellido = b.getString("Apellidos");
        usuario = b.getString("Usuario");
        email = b.getString("Email");
        contraseña = b.getString("Contraseña");
        */

        Log.d("Datos","Nombre: "+nombre+" Apellido: "+apellido+" Us: "+usuario+" Email: "+email+" Cont: "+contraseña);

        setGenderColor();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setApellidos(apellido);
                u.setPassword(contraseña);
                u.setEmail(email);
                u.setUsuario(usuario);

                u.setGenero(obtenGenero());
                u.setAltura(rs.getId());
                u.setPeso(Integer.parseInt(peso.getText().toString()));
                u.setEdad(Integer.parseInt(edad.getText().toString()));
                u.setActividadF(obtenActividadF());

                if(!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Intent i = new Intent(RegisterAvanzadoActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            case R.id.cvMasc:
                changeGenderColor();
                setGenderColor();

                break;
            case R.id.cvFem:
                changeGenderColor();
                setGenderColor();

                break;
        }
    }

    private int obtenActividadF() {
        return 0;
    }

    private int obtenGenero() {
        if(mascSeleccionado){
            return 0;
        } else{
            return 1;
        }
    }

    private void setGenderColor(){

        cvMale.setCardBackgroundColor(getBackgroundColor(mascSeleccionado));
        cvFemale.setCardBackgroundColor(getBackgroundColor(femSeleccionado));

    }

    private void changeGenderColor(){

        mascSeleccionado = !mascSeleccionado;
        femSeleccionado = !femSeleccionado;

    }

    private int getBackgroundColor(boolean isSelectedComponent){

        int colorReference = 0;

        if(isSelectedComponent){
            colorReference = R.color.dark_grey;
        } else {
            colorReference = R.color.white;
        }

        return ContextCompat.getColor(this,colorReference);

    }

}
