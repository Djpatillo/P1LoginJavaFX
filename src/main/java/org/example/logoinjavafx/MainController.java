package org.example.logoinjavafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TextField userNameField;
    @FXML private TextField movieTitleField;
    // Agregamos el campo para la contraseña que falta
    @FXML private TextField passwordField; 
    @FXML private Button saveButton;
    @FXML private TableView<Usuario> favoritesTable;
    @FXML private TableColumn<Usuario, String> userColumn;
    @FXML private TableColumn<Usuario, String> movieColumn;
    // Agregamos la columna para la contraseña que falta
    @FXML private TableColumn<Usuario, String> passwordColumn;

    // Lista observable que 'envuelve' los datos de la tabla
    private final ObservableList<Usuario> data = FXCollections.observableArrayList();

    /**
     * Se llama automáticamente después de cargar el FXML.
     * Perfecto para configurar la tabla y cargar datos iniciales.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 2. Configurar las columnas de la tabla
        // Le dice a la columna 'usuario' que use la propiedad 'usuario' del modelo Favorito.
        userColumn.setCellValueFactory(cellData -> cellData.getValue().id_usuarioProperty());
        // Le dice a la columna 'pelicula' que use la propiedad 'pelicula' del modelo Favorito.
        movieColumn.setCellValueFactory(cellData -> cellData.getValue().correoElectronicoProperty());

        // Corregido: Asignamos la contraseña a su propia columna, en lugar de sobreescribir movieColumn
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().contrasenhaProperty());

        // 3. Cargar los datos iniciales de la BD
        loadDataFromDatabase();
    }

    /**
     * Método que se llama al pulsar el botón (definido en el FXML 'onAction').
     */
    @FXML
    protected void handleSaveButton() {
        String id_usuario = userNameField.getText();
        String correoElectronico = movieTitleField.getText();
        // Corregido: Obtenemos la contraseña del campo correcto
        String contrasenha = passwordField.getText();

        // Validación simple
        if (id_usuario.isEmpty() || correoElectronico.isEmpty() || contrasenha.isEmpty()) {
            // Aquí podrías mostrar una Alerta (Alert)
            System.out.println("Los campos no pueden estar vacíos.");
            return;
        }

        // 4. Guardar en la base de datos
        // La lógica de "no guardar si ya existe" está en DatabaseManager
        boolean saved = DataBaseManager.saveUsuario(id_usuario, correoElectronico, contrasenha);

        if (saved) {
            // 5. Si se guardó, refrescar la tabla y limpiar campos
            loadDataFromDatabase(); // Recarga los datos de la BD
            userNameField.clear();
            movieTitleField.clear();
            // Limpiamos también el campo de contraseña
            passwordField.clear();
        } else {
            // Opcional: Mostrar aviso de que ya existía
            System.out.println("Ese favorito ya existe.");
        }
    }

    /**
     * Helper para cargar/refrescar los datos de la tabla.
     */
    private void loadDataFromDatabase() {
        data.clear(); // Limpia la lista actual
        data.addAll(DataBaseManager.getAllUsuarios()); // Añade los datos de la BD
        favoritesTable.setItems(data); // Asigna la lista a la tabla
    }
}