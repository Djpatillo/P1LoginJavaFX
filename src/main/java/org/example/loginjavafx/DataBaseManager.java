package org.example.loginjavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/loginjavafx?serverTimezone=UTC";
    private static final String USER = "root"; // Ej: "root"
    private static final String PASS = ""; // Ej: "admin"

    // Helper para obtener la conexión
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}