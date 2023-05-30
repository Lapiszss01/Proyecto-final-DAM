package com.example.proyectofinaldam.data.model;

public class Ejercicios {
    String nombre;
    Categorias categoria, segCategoria;
    int foto, repeticiones, video;
    Boolean isSelected = false;

    public Ejercicios(String nombre, Categorias categoria, int foto,int repeticiones, int video, Categorias segCategoria) {
        this.categoria = categoria;
        this.segCategoria = segCategoria;
        this.nombre = nombre;
        this.foto = foto;
        this.repeticiones = repeticiones;
        this.video = video;
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

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    public Categorias getSegCategoria() {
        return segCategoria;
    }

    public void setSegCategoria(Categorias segCategoria) {
        this.segCategoria = segCategoria;
    }
}
