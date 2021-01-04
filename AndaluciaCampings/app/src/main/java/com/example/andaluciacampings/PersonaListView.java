package com.example.andaluciacampings;

public class PersonaListView {

    String nombre, apellido;
    int nivel;

    public PersonaListView(String nombre, String apellido, int nivel) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getNivel() {
        return nivel;
    }
}
