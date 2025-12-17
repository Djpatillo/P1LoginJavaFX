package org.example.loginjavafx.dao;

import org.example.loginjavafx.model.Usuario;
import java.sql.*;

public class UsuarioDAO {
    public Usuario login(String user, String password) {

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("nombre_completo")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}