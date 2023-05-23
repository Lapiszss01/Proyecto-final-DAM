package com.example.proyectofinaldam.data.model;

public class Usuario {

    //Esto es básicamente un usuario con los datos que puede guardar

    int id;
    String nombre, apellidos, usuario, password,email;

    int genero,altura,peso,edad,actividadF;

    public Usuario(){

    }

    public boolean isNull(){
        if(nombre.equals("")&&apellidos.equals("")&&usuario.equals("")&&password.equals("")){
            return false;
        } else {return true;}
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", genero=" + genero +
                ", altura=" + altura +
                ", peso=" + peso +
                ", edad=" + edad +
                ", actividadF=" + actividadF +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getActividadF() {
        return actividadF;
    }

    public void setActividadF(int actividadF) {
        this.actividadF = actividadF;
    }
}
