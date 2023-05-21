package com.example.proyectofinaldam.data.model;

public class Ejercicios {
    String nombre;
    Categorias categoria;
    Boolean isSelected = false;

    public Ejercicios(String nombre, Categorias categoria) {
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
