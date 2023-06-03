package com.example.proyectofinaldam.data.model;

import java.io.Serializable;

public class Cancion implements Serializable {

    private String path;
    private String titulo;
    private String artista;
    private String album;

    public Cancion(String titulo, String path, String artista, String album) {
        this.titulo = titulo;
        this.artista = artista;
        this.path = path;
        this.album = album;

    }

    public Cancion() {
        this("","","","");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


}
