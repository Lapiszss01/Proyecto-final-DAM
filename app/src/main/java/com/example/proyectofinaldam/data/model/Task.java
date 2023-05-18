package com.example.proyectofinaldam.data.model;

public class Task {
    String nombre;
    TaskCategory categoria;
    Boolean isSelected = false;

    public Task() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TaskCategory getCategoria() {
        return categoria;
    }

    public void setCategoria(TaskCategory categoria) {
        this.categoria = categoria;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
