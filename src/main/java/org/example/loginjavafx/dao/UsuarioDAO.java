package org.example.loginjavafx.dao;

import org.example.loginjavafx.model.Usuario;
import java.sql.*;

public class UsuarioDAO {
    public Usuario login(String correo, String password) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Login exitoso, devolvemos el objeto Usuario
                return new Usuario(rs.getInt("id_usuario"), rs.getString("correo"), rs.getString("nombre"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null; // Login fallido
    }
}