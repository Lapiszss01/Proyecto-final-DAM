package com.example.proyectofinaldam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectofinaldam.ui.Login.LoginActivity;
import com.example.proyectofinaldam.ui.Login.RegisterActivity;
import com.example.proyectofinaldam.ui.Login.ViewPager2.VPAdapter;
import com.example.proyectofinaldam.ui.Login.ViewPager2.firstFragment;
import com.example.proyectofinaldam.ui.Login.ViewPager2.secondFragment;
import com.example.proyectofinaldam.ui.Login.ViewPager2.thirdFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart;
    TextView txtLogin;

    ViewPager2 vp2;
    VPAdapter vpAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btn_Start);
        txtLogin = (TextView) findViewById(R.id.txt_Login) ;
        btnStart.setOnClickListener(this);
        txtLogin.setOnClickListener(this);

        vp2 = (ViewPager2) findViewById(R.id.vp2);
        vpAdapter = new VPAdapter(getSupportFragmentManager(), getLifecycle());

        // add Fragments in your ViewPagerFragmentAdapter class
        vpAdapter.addFragment(new firstFragment());
        vpAdapter.addFragment(new secondFragment());
        vpAdapter.addFragment(new thirdFragment());

        // set Orientation in your ViewPager2
        vp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        vp2.setAdapter(vpAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Start:
                // Al pulsar botón registro
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.txt_Login:
                // Al pulsar boton login
                Intent i2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i2);
                break;
        }
    }
}