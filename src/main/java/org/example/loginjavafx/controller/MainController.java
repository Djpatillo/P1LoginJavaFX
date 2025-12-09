package org.example.loginjavafx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.loginjavafx.dao.TareaDAO;
import org.example.loginjavafx.model.Tarea;
import org.example.loginjavafx.model.Usuario;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // UI
    @FXML private Label lblUser;
    @FXML private TextField txtTitulo;
    @FXML private RadioButton rbAlta;
    @FXML private Slider sliderProgreso;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Tarea> tabla;
    @FXML private TableColumn<Tarea, String> colTitulo, colPrioridad, colDias, colEstado;

    private Usuario usuario;
    private TareaDAO tareaDAO = new TareaDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar columnas de tabla
        colTitulo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitulo()));
        colPrioridad.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPrioridad()));

        // REQUISITO: Datos calculados mostrados en vista
        colDias.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDiasRestantes() + " días"));
        colEstado.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEstadoTexto()));
    }

    // Método llamado desde el LoginController
    public void initData(Usuario usuario) {
        this.usuario = usuario;
        lblUser.setText("Usuario: " + usuario.getNombre());
        actualizarTabla();
    }

    private void actualizarTabla() {
        tabla.setItems(FXCollections.observableArrayList(tareaDAO.obtenerPorUsuario(usuario.getId())));
    }

    @FXML
    public void guardarTarea() {
        String prioridad = rbAlta.isSelected() ? "ALTA" : "BAJA";
        LocalDate fecha = datePicker.getValue() != null ? datePicker.getValue() : LocalDate.now().plusDays(7);

        Tarea nueva = new Tarea(0, txtTitulo.getText(), prioridad, (int)sliderProgreso.getValue(), fecha);

        tareaDAO.guardar(nueva, usuario.getId());
        actualizarTabla(); // Refrescar vista
    }
}