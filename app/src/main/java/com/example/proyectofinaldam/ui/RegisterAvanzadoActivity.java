package com.example.proyectofinaldam.ui;

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
import com.example.proyectofinaldam.data.sql.datosUsuario;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

public class RegisterAvanzadoActivity extends AppCompatActivity implements View.OnClickListener {

    public String nombre,apellido,usuario,email,contraseña;
    public boolean mascSeleccionado = true;
    public boolean femSeleccionado = false;
    public int altura,actividadF;

    public RadioButton rb0,rb1,rb2,rb3;
    public RadioGroup rg0,rg1;

    public TextView tvRs;
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
        rs.setOnClickListener(this);
        cvMale = (MaterialCardView) findViewById(R.id.cvMasc);
        cvMale.setOnClickListener(this);
        cvFemale = (MaterialCardView) findViewById(R.id.cvFem);
        cvFemale.setOnClickListener(this);
        btnRegistro = (Button) findViewById(R.id.btnRegister);
        btnRegistro.setOnClickListener(this);
        tvRs = (TextView) findViewById(R.id.tvHeight);

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
        dao = new datosUsuario(this);
        rs.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                    altura = Math.round(value);
                    tvRs.setText(Integer.toString(altura));
            }
        });

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
                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setApellidos(apellido);
                u.setPassword(contraseña);
                u.setEmail(email);
                u.setUsuario(usuario);

                u.setGenero(obtenGenero());
                u.setAltura(altura);
                u.setPeso(Integer.parseInt(peso.getText().toString()));
                u.setEdad(Integer.parseInt(edad.getText().toString()));
                u.setActividadF(actividadF);

                Log.d("User","User: "+u.toString());

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
                Log.d("a","Masc "+mascSeleccionado);
                Log.d("a","Fem "+femSeleccionado);

                break;
            case R.id.cvFem:
                changeGenderColor();
                setGenderColor();
                Log.d("a","Masc "+mascSeleccionado);
                Log.d("a","Fem "+femSeleccionado);
                break;

            case R.id.radioButton0:
                rg1.clearCheck();
                actividadF = 0;
                break;
            case R.id.radioButton1:
                rg1.clearCheck();
                actividadF = 1;
                break;
            case R.id.radioButton2:
                rg0.clearCheck();
                actividadF = 2;
                break;
            case R.id.radioButton3:
                rg0.clearCheck();
                actividadF = 3;
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
            colorReference = R.color.dark_grey;
        } else {
            colorReference = R.color.white;
        }

        return ContextCompat.getColor(this,colorReference);

    }

}
