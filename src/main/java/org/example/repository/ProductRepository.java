package org.example.repository;

import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final String URL = "jdbc:mariadb://localhost:3316/MinisoDB"; // Cambia esto con tu port, a mi no se bien random me coge 3316 pero por lo general es 3306
    private final String USER = "root"; // Cambia esto segun tu perfil de mariadb compadre
    private final String PASSWORD = "migue"; // y la contraseña tambien ni modo pues

    // Metodo para agregar un producto
    public void addProduct(String name, String category, double price, int stock) {
        String query = "INSERT INTO Products (name, category, price, stock) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setInt(4, stock);
            statement.executeUpdate();
            System.out.println("Producto agregado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al agregar producto: " + e.getMessage());
        }
    }

    // Metodo para obtener todos los productos
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                products.add(new Product(productId, name, category, price, stock));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return products;
    }
}
