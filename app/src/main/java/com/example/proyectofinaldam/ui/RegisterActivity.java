package com.example.proyectofinaldam.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.datosUsuario;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText us, pass,confPass, nom, ap,email;
    Button btnRegisto;
    datosUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_0);

        email = (EditText) findViewById(R.id.etEmail);
        pass = (EditText) findViewById(R.id.etPassword);
        us = (EditText) findViewById(R.id.etUsuario);
        confPass = (EditText) findViewById(R.id.etConfirmPassword);
        nom = (EditText) findViewById(R.id.etNombre);
        ap = (EditText) findViewById(R.id.etApellidos);
        btnRegisto = (Button) findViewById(R.id.btnRegister);
        btnRegisto.setOnClickListener(this);
        dao = new datosUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Usuario u = new Usuario();
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setEmail(email.getText().toString());
                u.setUsuario(us.getText().toString());

                if(!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.insertUsuario(u)){
                    if(!pass.getText().toString().equals(confPass.getText().toString())){
                        Toast.makeText(this,"Las contraseñas deben ser iguales",Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_LONG).show();
                        finish();
                    }

                }else{
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
