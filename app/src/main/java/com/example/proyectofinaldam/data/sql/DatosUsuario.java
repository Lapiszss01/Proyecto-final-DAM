package com.example.proyectofinaldam.data.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyectofinaldam.data.model.Usuario;

import java.util.ArrayList;

public class DatosUsuario {

    Context c;
    Usuario u;
    ArrayList<Usuario> lista;

    SQLiteDatabase sql;
    String bd = "BDUsuarios";
    //Crea la base de datos
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario text, pass text, nombre text, ap text,email text,genero integer,altura integer,peso integer,edad integer,actividadF integer)";

    public DatosUsuario(Context c){
        this.c=c;
        sql = c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        //Inserta un usuario con las tablas que hay entre comillas
        if(buscar(u.getUsuario())==0){
            ContentValues cv = new ContentValues();
            cv.put("usuario",u.getUsuario());
            cv.put("pass",u.getPassword());
            cv.put("nombre",u.getNombre());
            cv.put("ap",u.getApellidos());
            cv.put("email",u.getEmail());
            cv.put("genero",u.getGenero());
            cv.put("altura",u.getAltura());
            cv.put("peso",u.getPeso());
            cv.put("edad",u.getEdad());
            cv.put("actividadF",u.getActividadF());
            return (sql.insert("usuario",null,cv)>0);
        }else {return false;}
    }

    private int buscar(String u) {
        //Busca un usuario en la bd
        int x=0;
        lista = selectUsuario();
        for (Usuario us:lista){
            if(us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuario(){
        //Devuelve una lista con todos los Usuarios
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cursor = sql.rawQuery("select * from usuario",null);
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cursor.getInt(0));
                u.setUsuario(cursor.getString(1));
                u.setPassword(cursor.getString(2));
                u.setNombre(cursor.getString(3));
                u.setApellidos(cursor.getString(4));
                u.setEmail(cursor.getString(5));
                u.setGenero(cursor.getInt(6));
                u.setAltura(cursor.getInt(7));
                u.setPeso(cursor.getInt(8));
                u.setEdad(cursor.getInt(9));
                u.setActividadF(cursor.getInt(10));
                lista.add(u);
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p){
        //Si el usuario y contraseña coinciden en la bd te deja loggear
        int a = 0;
        Cursor cursor = sql.rawQuery("select * from usuario",null);
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                if(cursor.getString(1).equals(u)&&cursor.getString(2).equals(p)){
                    a++;
                }
            }while (cursor.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista = selectUsuario();
        for (Usuario us :lista) {
            if(us.getUsuario().equals(u)&&us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }
    public Usuario getUsuarioById(int id){
        lista = selectUsuario();
        for (Usuario us :lista) {
            if(us.getId() == id){
                return us;
            }
        }
        return null;
    }

    public boolean updateUsuario(Usuario u){
        ContentValues cv = new ContentValues();
        cv.put("altura",u.getAltura());
        cv.put("edad",u.getEdad());
        cv.put("peso",u.getPeso());
        cv.put("actividadF",u.getActividadF());
        return (sql.update("usuario",cv,"id="+u.getId(),null)>0);
    }


}
