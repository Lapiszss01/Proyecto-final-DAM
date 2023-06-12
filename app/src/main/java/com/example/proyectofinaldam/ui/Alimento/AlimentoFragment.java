package com.example.proyectofinaldam.ui.Alimento;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectofinaldam.MainActivity;
import com.example.proyectofinaldam.R;
import com.example.proyectofinaldam.data.model.Usuario;
import com.example.proyectofinaldam.ui.Login.RegisterActivity;
import com.example.proyectofinaldam.ui.Login.RegisterAvanzadoActivity;

public class AlimentoFragment extends Fragment implements View.OnClickListener{

    Usuario u;
    CardView cvPerderGrasa, cvMantenimiento, cvGanarMusculo;
    protected View.OnClickListener onClickListener;

    public AlimentoFragment(Usuario u){
        this.u = u;
    }
    //Nada por ahora

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimento, container, false);

        cvGanarMusculo = view.findViewById(R.id.cvGanarMusculo);
        cvGanarMusculo.setOnClickListener(this);
        cvMantenimiento = view.findViewById(R.id.cvMantenimiento);
        cvMantenimiento.setOnClickListener(this);
        cvPerderGrasa = view.findViewById(R.id.cvPerderGrasa);
        cvPerderGrasa.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvGanarMusculo:
                Log.d("OnClick","Aa");
                Intent i = new Intent(getActivity(), AlimentoActivity.class);
                startActivity(i);
                break;
            case R.id.cvMantenimiento:
                Log.d("OnClick","Aav");
                Intent i2 = new Intent(getActivity(), AlimentoActivity.class);
                startActivity(i2);
                break;
            case R.id.cvPerderGrasa:
                Log.d("OnClick","Aads");
                Intent i3 = new Intent(getActivity(), AlimentoActivity.class);
                startActivity(i3);
                break;
        }
    }
}

