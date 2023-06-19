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

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.DatosUsuario;

public class RegisterAvanzado2Activity extends AppCompatActivity implements View.OnClickListener {

    public String nombre,apellido,usuario,email,contraseña;
    public int genero,altura,peso,edad;

    public CardView cvSedentario, cvLigero, cvModerado, cvAlto;

    DatosUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_2);

        cvSedentario = (CardView) findViewById(R.id.cvSedentario);
        cvSedentario.setOnClickListener(this);
        cvLigero = (CardView) findViewById(R.id.cvLigero);
        cvLigero.setOnClickListener(this);
        cvModerado = (CardView) findViewById(R.id.cvModerado);
        cvModerado.setOnClickListener(this);
        cvAlto = (CardView) findViewById(R.id.cvAlto);
        cvAlto.setOnClickListener(this);

        dao = new DatosUsuario(this);

        //Pilla los datos de la vista anterior
        Bundle b = getIntent().getExtras();
        nombre = b.getString("Nombre");
        apellido = b.getString("Apellidos");
        usuario = b.getString("Usuario");
        email = b.getString("Email");
        contraseña = b.getString("Contraseña");
        genero = b.getInt("Genero");
        altura = b.getInt("Altura");
        peso = b.getInt("Peso");
        edad = b.getInt("Edad");

        Log.d("A",nombre+apellido+usuario+email+contraseña+genero+altura+peso+edad);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvSedentario:
                Usuario u = new Usuario();
                u.setNombre(nombre);
                u.setApellidos(apellido);
                u.setPassword(contraseña);
                u.setEmail(email);
                u.setUsuario(usuario);

                u.setGenero(genero);
                u.setAltura(altura);
                u.setPeso(peso);
                u.setEdad(edad);
                u.setActividadF(0);

                Log.d("User",u.toString())                ;

                if(dao.insertUsuario(u)){
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    finish();
                } else{
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                    finish();}
                break;

            case R.id.cvLigero:
                Usuario u2 = new Usuario();
                u2.setNombre(nombre);
                u2.setApellidos(apellido);
                u2.setPassword(contraseña);
                u2.setEmail(email);
                u2.setUsuario(usuario);

                u2.setGenero(genero);
                u2.setAltura(altura);
                u2.setPeso(peso);
                u2.setEdad(edad);
                u2.setActividadF(1);

                if(dao.insertUsuario(u2)){
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                    finish();}
                break;

            case R.id.cvModerado:
                Usuario u3 = new Usuario();
                u3.setNombre(nombre);
                u3.setApellidos(apellido);
                u3.setPassword(contraseña);
                u3.setEmail(email);
                u3.setUsuario(usuario);

                u3.setGenero(genero);
                u3.setAltura(altura);
                u3.setPeso(peso);
                u3.setEdad(edad);
                u3.setActividadF(2);

                if(dao.insertUsuario(u3)){
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                    finish();}
                break;
            case R.id.cvAlto:
                Usuario u4 = new Usuario();
                u4.setNombre(nombre);
                u4.setApellidos(apellido);
                u4.setPassword(contraseña);
                u4.setEmail(email);
                u4.setUsuario(usuario);

                u4.setGenero(genero);
                u4.setAltura(altura);
                u4.setPeso(peso);
                u4.setEdad(edad);
                u4.setActividadF(3);

                if(dao.insertUsuario(u4)){
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Intent i = new Intent(RegisterAvanzado2Activity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                    finish();}
                break;

        }
    }
}
