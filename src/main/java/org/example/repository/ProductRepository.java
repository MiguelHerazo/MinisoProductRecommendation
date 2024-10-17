package org.example.repository;

import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final String url = "jdbc:mariadb://localhost:3316/MinisoDB"; // Cambiar según tu configuración
    private final String user = "root"; // Cambiar según tu configuración
    private final String password = "migue"; // Cambiar según tu configuración

    // Método para establecer la conexión a la base de datos
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Método para agregar un nuevo producto
    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getCategory());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.executeUpdate();
            System.out.println("Producto agregado: " + product.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para obtener un producto por ID
    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM Products WHERE product_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    // Método para obtener todos los productos
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    // Método para actualizar un producto
    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET name = ?, category = ?, price = ?, stock = ? WHERE product_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getCategory());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStock());
            pstmt.setInt(5, product.getId()); // Asegúrate de tener un metodo getId() en tu modelo Product
            pstmt.executeUpdate();
            System.out.println("Producto actualizado: " + product.getName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un producto
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            System.out.println("Producto eliminado con ID: " + productId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
