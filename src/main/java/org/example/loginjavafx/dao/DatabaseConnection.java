package org.example.loginjavafx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Ajusta usuario y contraseña según tu MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/loginjavafx";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}