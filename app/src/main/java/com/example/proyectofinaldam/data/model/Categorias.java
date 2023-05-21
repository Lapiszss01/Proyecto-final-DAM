package com.example.proyectofinaldam.data.model;

public class Categorias {

    int type;


    public Categorias(Boolean isSelected, int type){
        isSelected = true;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
