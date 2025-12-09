package org.example.loginjavafx.dao;

import org.example.loginjavafx.model.Tarea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {
    public List<Tarea> obtenerPorUsuario(int idUsuario) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE id_usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Tarea(
                        rs.getInt("id_tarea"),
                        rs.getString("titulo"),
                        rs.getString("prioridad"),
                        rs.getInt("progreso"),
                        rs.getDate("fecha_limite").toLocalDate()
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public void guardar(Tarea t, int idUsuario) {
        String sql = "INSERT INTO tareas (titulo, prioridad, progreso, fecha_limite, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getTitulo());
            stmt.setString(2, t.getPrioridad());
            stmt.setInt(3, t.getProgreso());
            stmt.setDate(4, Date.valueOf(t.getFecha_limite()));
            stmt.setInt(5, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}