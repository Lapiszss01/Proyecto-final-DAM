package com.example.proyectofinaldam.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.data.sql.DatosUsuario;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText us, pass,confPass, nom, ap,email;
    Button btnRegisto;
    DatosUsuario dao;

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
        dao = new DatosUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                //Cuando pulsas registrar pilla los datos de cada cuadro de texto

                Usuario u = new Usuario();
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setEmail(email.getText().toString());
                u.setUsuario(us.getText().toString());

                //Comprobacion de que ningún campo esté vacio
                if(email.getText().toString().isEmpty()||pass.getText().toString().isEmpty()||us.getText().toString().isEmpty()||confPass.getText().toString().isEmpty()||nom.getText().toString().isEmpty()||ap.getText().toString().isEmpty()){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(!pass.getText().toString().equals(confPass.getText().toString())){
                    Toast.makeText(this,"Las contraseñas deben ser iguales",Toast.LENGTH_LONG).show();
                    } else{
                        //Si ningún campo está vacio pasa los datos a la siguiente vista(Register activity)
                        Intent i = new Intent(RegisterActivity.this, RegisterAvanzadoActivity.class);
                        i.putExtra("Nombre", nom.getText().toString());
                        i.putExtra("Apellidos", ap.getText().toString());
                        i.putExtra("Contraseña", pass.getText().toString());
                        i.putExtra("Usuario", us.getText().toString());
                        i.putExtra("Email", email.getText().toString());
                        startActivity(i);
                        finish();
                    }
                break;
        }
    }
}
