package org.example.loginjavafx.model;

public class Usuario {
    private int id;
    private String username; // ANTES: correo
    private String nombreCompleto;

    public Usuario(int id, String username, String nombreCompleto) {
        this.id = id;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; } // Getter actualizado
    public String getNombreCompleto() { return nombreCompleto; }
}