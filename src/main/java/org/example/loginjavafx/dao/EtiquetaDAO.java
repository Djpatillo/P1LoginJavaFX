package org.example.loginjavafx.dao;

import org.example.loginjavafx.model.Etiqueta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtiquetaDAO {
    public List<Etiqueta> obtenerTodas() {
        List<Etiqueta> lista = new ArrayList<>();
        String sql = "SELECT * FROM etiquetas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Etiqueta(rs.getInt("id_etiqueta"), rs.getString("nombre")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}