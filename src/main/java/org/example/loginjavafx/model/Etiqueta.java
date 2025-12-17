package org.example.loginjavafx.model;

public class Etiqueta {
    private int id;
    private String nombre;

    public Etiqueta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    // Importante: El ComboBox usa este método para mostrar el texto
    @Override
    public String toString() {
        return nombre;
    }
}