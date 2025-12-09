package org.example.loginjavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Usuario {
    // Se cambian los campos String por StringProperty para compatibilidad con JavaFX
    private final StringProperty correo_electronico;
    private final StringProperty contrasenha;
    private final StringProperty id_usuario;

    public Usuario(String correo_electronico, String contrasena, String id_usuario) {
        // Se inicializan las propiedades
        this.correo_electronico = new SimpleStringProperty(correo_electronico);
        this.contrasenha = new SimpleStringProperty(contrasena);
        this.id_usuario = new SimpleStringProperty(id_usuario);
    }

    public Usuario(String correoElectronico, String contrasenha) {
        // Se llama al constructor principal para evitar campos nulos
        this(correoElectronico, contrasenha, "");
    }

    public String getCorreo_electronico() {
        return correo_electronico.get();
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico.set(correo_electronico);
    }

    public String getId_usuario() {
        return id_usuario.get();
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario.set(id_usuario);
    }

    // Nota: En HelloController usas .getContrasena() como Property en setCellValueFactory.
    // Para mantener compatibilidad, este método devuelve ObservableValue, aunque por convención
    // un getter debería devolver String y el método property() devolver el ObservableValue.
    public ObservableValue<String> getContrasena() {
        return contrasenha;
    }

    public void setContrasena(String contrasena) {
        this.contrasenha.set(contrasena);
    }

    public ObservableValue<String> id_usuarioProperty() {
        return id_usuario;
    }

    public ObservableValue<String> correoElectronicoProperty() {
        return correo_electronico;
    }

    public ObservableValue<String> contrasenhaProperty(){
        return contrasenha;
    }
}
