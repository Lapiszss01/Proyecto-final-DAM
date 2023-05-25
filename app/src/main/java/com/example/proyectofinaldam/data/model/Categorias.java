package com.example.proyectofinaldam.data.model;

public class Categorias {

    int type;
    String nombre;

    public Categorias(Boolean isSelected, int type,String nombre){
        isSelected = true;
        this.type = type;
        this.nombre=nombre;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
