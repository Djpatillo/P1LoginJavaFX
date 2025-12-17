package org.example.loginjavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.loginjavafx.HelloApplication;
import org.example.loginjavafx.dao.UsuarioDAO;
import org.example.loginjavafx.model.Usuario;

public class LoginController {
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;

    @FXML
    public void onLogin() {
        UsuarioDAO dao = new UsuarioDAO();
        // Intentamos loguear con lo que escribió el usuario
        Usuario usuario = dao.login(txtUsuario.getText(), txtPassword.getText());

        if (usuario != null) {
            abrirPrincipal(usuario);
        } else {
            new Alert(Alert.AlertType.ERROR, "Credenciales incorrectas").show();
        }
    }

    private void abrirPrincipal(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(loader.load());

            // Pasar el usuario a la siguiente ventana
            org.example.loginjavafx.controller.MainController controller = loader.getController();
            controller.initData(usuario);

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}