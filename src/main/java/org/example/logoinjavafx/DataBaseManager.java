package org.example.logoinjavafx;

import java.lang.foreign.MemorySegment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/LoginJavaFX?serverTimezone=UTC";
    private static final String USER = "root"; // Ej: "root"
    private static final String PASS = ""; // Ej: "admin"

    // Helper para obtener la conexión
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public static void initializeDatabase() {
        // VARCHAR(255) es más que suficiente para nombres y títulos
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                + " id_usuario VARCHAR(255) NOT NULL,"
                + " correoElectronico VARCHAR(255) NOT NULL,"
                + " contrasenha VARCHAR(255) NOT NULL,"
                + " PRIMARY KEY (id_usuario)"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }

    public static boolean saveUsuario(String id_usuario, String correoElectronico, String contrasena) {
        String sql = "INSERT INTO usuarios(id_usuario, correoElectronico, contrasenha) VALUES(?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id_usuario);
            pstmt.setString(2, correoElectronico);
            pstmt.setString(3, contrasena);

            pstmt.executeUpdate();
            return true; // Éxito

        } catch (SQLException e) {
            // --- ¡CAMBIO IMPORTANTE EN EL ERROR! ---
            // El código de error de MySQL para "Duplicate entry" (clave primaria duplicada) es 1062.
            if (e.getErrorCode() == 1062) {
                // Ya existe, no es un error de la aplicación, solo informamos.
                System.out.println("El usuario ya existe (PK duplicada).");
            } else {
                // Otro error de SQL
                System.err.println("Error al guardar en MySQL: " + e.getMessage());
            }
            return false; // Fallo (duplicado u otro error)
        }
    }

    public static List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, correoElectronico, contrasenha FROM usuarios";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // El constructor de Usuario es: (correo, contrasena, id)
                Usuario usu = new Usuario(
                        rs.getString("correoElectronico"),
                        rs.getString("contrasenha"),
                        rs.getString("id_usuario")
                );
                usuarios.add(usu);
            }
        } catch (SQLException e) {
            System.err.println("Error al leer de MySQL: " + e.getMessage());
        }
        return usuarios;
    }
}
