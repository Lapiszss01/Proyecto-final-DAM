package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinaldam.data.sql.datosUsuario;
import com.example.proyectofinaldam.ui.RegisterActivity;
import com.example.proyectofinaldam.ui.RegisterAvanzadoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRegisto, btnLogin;
    EditText user, pass;
    datosUsuario dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.etUser);
        pass = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegisto = (Button) findViewById(R.id.btnRegister);
        btnRegisto.setOnClickListener(this);

        dao = new datosUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.btnLogin:
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Toast.makeText(this,"Login funcional",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(this,"Datos erroneos",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}