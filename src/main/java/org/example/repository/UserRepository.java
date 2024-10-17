package org.example.repository;

import org.example.model.User;

import java.sql.*;

public class UserRepository {
    private final String url = "jdbc:mariadb://localhost:3316/MinisoDB";
    private final String user = "root"; // Cambia esto según tu configuración
    private final String password = "migue"; // Cambia esto según tu configuración

    // Método para establecer la conexión a la base de datos
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Agregar un nuevo usuario
    public void addUser(User user) {
        String sql = "INSERT INTO Users (name, email) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            System.out.println("Usuario agregado: " + user.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
