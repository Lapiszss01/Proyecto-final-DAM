package com.example.proyectofinaldam.ui.Login;

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
import androidx.core.content.ContextCompat;

import com.example.proyectofinaldam.MainActivity;
import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.DatosUsuario;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.RangeSlider;

public class RegisterAvanzadoActivity extends AppCompatActivity implements View.OnClickListener {

    public String nombre,apellido,usuario,email,contraseña;
    public boolean mascSeleccionado = true;
    public boolean femSeleccionado = false;
    public int altura = 120;
    public int actividadF = -1;

    public RadioButton rb0,rb1,rb2,rb3;
    public RadioGroup rg0,rg1;

    public TextView tvRs, tvAf;
    public EditText peso,edad;
    public RangeSlider rs;
    public CardView cvMale, cvFemale;
    Button btnRegistro;
    DatosUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_1);

        peso = (EditText) findViewById(R.id.etWeight);
        edad = (EditText) findViewById(R.id.etAge);
        rs = (RangeSlider) findViewById(R.id.rsHeight);
        rs.setOnClickListener(this);
        cvMale = (MaterialCardView) findViewById(R.id.cvMasc);
        cvMale.setOnClickListener(this);
        cvFemale = (MaterialCardView) findViewById(R.id.cvFem);
        cvFemale.setOnClickListener(this);
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
        nombre = b.getString("Nombre");
        apellido = b.getString("Apellidos");
        usuario = b.getString("Usuario");
        email = b.getString("Email");
        contraseña = b.getString("Contraseña");


        Log.d("Datos","Nombre: "+nombre+" Apellido: "+apellido+" Us: "+usuario+" Email: "+email+" Cont: "+contraseña);

        setGenderColor();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                //Al pulsar en registro pilla los datos puestos, comprueba que no haya más campos vacios e inserta el usuario en la base de datos
                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setApellidos(apellido);
                u.setPassword(contraseña);
                u.setEmail(email);
                u.setUsuario(usuario);

                u.setGenero(obtenGenero());
                u.setAltura(altura);
                if(!peso.getText().toString().equals("")){u.setPeso(Integer.parseInt(peso.getText().toString()));}
                if(!edad.getText().toString().equals("")){u.setEdad(Integer.parseInt(edad.getText().toString()));}
                u.setActividadF(actividadF);

                Log.d("User","User: "+u.toString());

                if(!u.isNull()||peso.equals("")||edad.equals("")||actividadF == -1){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    Intent i = new Intent(RegisterAvanzadoActivity.this, LoginActivity.class);
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
            colorReference = R.color.blueSelected;
        } else {
            colorReference = R.color.blueButton;
        }

        return ContextCompat.getColor(this,colorReference);

    }

}
