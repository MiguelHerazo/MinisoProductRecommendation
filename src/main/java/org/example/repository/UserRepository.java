package org.example.repository;

import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final String URL = "jdbc:mariadb://localhost:3316/MinisoDB"; // Cambia esto con tu port, a mi no se bien random me coge 3316 pero por lo general es 3306
    private final String USER = "root"; // Cambia esto segun tu perfil de mariadb compadre
    private final String PASSWORD = "migue"; // y la contraseña tambien ni modo pues

    // Metodo para agregar un usuario
    public void addUser(String email, String name) {
        String query = "INSERT INTO users (email, name) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email.trim().toLowerCase()); // Normaliza el correo
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Usuario agregado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    // Metodo para obtener todos los usuarios
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                users.add(new User(userId, email, name));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
        return users;
    }
}
