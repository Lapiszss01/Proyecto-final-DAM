package com.example.proyectofinaldam.data.model;

public class Categorias {

    int type;
    String nombre;
    boolean isSelected;

    public Categorias(Boolean isSelected, int type,String nombre){
        this.isSelected = isSelected;
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

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
