package com.example.andaluciacampings;

public class UsuarioAplicacion {
    private String nombre;
    private UsuarioAplicacion(){

    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    private static UsuarioAplicacion usuarioAplicacion;
    public static UsuarioAplicacion get(){
        if(usuarioAplicacion == null){
            usuarioAplicacion = new UsuarioAplicacion();
        }
        return usuarioAplicacion;
    }
}
