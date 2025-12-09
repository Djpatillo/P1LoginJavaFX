package org.example.loginjavafx.model;

public class Usuario {
    private int id;
    private String correo;
    private String nombre;

    public Usuario(int id, String correo, String nombre) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
}