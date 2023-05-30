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
import com.example.proyectofinaldam.ui.Login.RegisterActivity;
import com.example.proyectofinaldam.ui.MainHubActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRegisto, btnLogin;
    EditText user, pass;
    DatosUsuario dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.etUser);
        pass = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegisto = (Button) findViewById(R.id.btnRegister);
        btnRegisto.setOnClickListener(this);

        dao = new DatosUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                // Al pulsar botón registro
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.btnLogin:
                // Al pulsar boton login
                String u = user.getText().toString();
                String p = pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if(dao.login(u,p)==1){
                    Usuario user = dao.getUsuario(u,p);
                    Toast.makeText(this,"Login funcional",Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(LoginActivity.this, MainHubActivity.class);
                    i2.putExtra("Id", user.getId());
                    startActivity(i2);
                } else{
                    Toast.makeText(this,"Datos erroneos",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}