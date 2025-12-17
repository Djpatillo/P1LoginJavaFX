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

    public void guardar(Tarea t, int idUsuario, int idEtiqueta) {
        String sqlTarea = "INSERT INTO tareas (titulo, prioridad, progreso, fecha_limite, id_usuario) VALUES (?, ?, ?, ?, ?)";
        String sqlRelacion = "INSERT INTO tareas_etiquetas (id_tarea, id_etiqueta) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement stmtTarea = null;
        PreparedStatement stmtRelacion = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Insertar Tarea
            stmtTarea = conn.prepareStatement(sqlTarea, Statement.RETURN_GENERATED_KEYS);
            stmtTarea.setString(1, t.getTitulo());
            stmtTarea.setString(2, t.getPrioridad());
            stmtTarea.setInt(3, t.getProgreso());
            stmtTarea.setDate(4, Date.valueOf(t.getFecha_limite()));
            stmtTarea.setInt(5, idUsuario);
            stmtTarea.executeUpdate();

            // 2. Obtener ID generado
            generatedKeys = stmtTarea.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idNuevaTarea = generatedKeys.getInt(1);


                stmtRelacion = conn.prepareStatement(sqlRelacion);
                stmtRelacion.setInt(1, idNuevaTarea);
                stmtRelacion.setInt(2, idEtiqueta);
                stmtRelacion.executeUpdate();
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try { if (generatedKeys != null) generatedKeys.close(); } catch (SQLException e) {}
            try { if (stmtTarea != null) stmtTarea.close(); } catch (SQLException e) {}
            try { if (stmtRelacion != null) stmtRelacion.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }
}