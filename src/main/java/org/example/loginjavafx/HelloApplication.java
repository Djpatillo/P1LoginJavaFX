package org.example.loginjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Pantalla de Registro");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // Capturamos cualquier error durante la carga de la UI
            e.printStackTrace(); // Imprime el error detallado en consola para el desarrollador
            
            // Muestra una alerta visual al usuario
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Crítico");
            alert.setHeaderText("No se pudo iniciar la aplicación");
            alert.setContentText("Ocurrió un error al cargar la vista: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
