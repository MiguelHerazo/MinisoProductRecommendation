package org.example.repository;

import org.example.model.Purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryRepository {
    private final String url = "jdbc:mariadb://localhost:3316/MinisoDB"; // Cambia el nombre de la base de datos si es necesario
    private final String user = "root"; // Cambia esto según tu configuración
    private final String password = "migue"; // Cambia esto según tu configuración

    // Metodo para establecer la conexión a la base de datos
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Registrar una nueva compra
    public void createPurchase(int userId, int productId) {
        String sql = "INSERT INTO PurchaseHistory (user_id, product_id) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("Compra registrada para el usuario ID: " + userId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtener el historial de compras de un usuario
    public List<Purchase> getPurchaseHistory(int userId) {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT * FROM PurchaseHistory WHERE user_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Purchase purchase = new Purchase(
                        rs.getInt("purchase_id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getDate("purchase_date") // Este campo debe ser añadido en la tabla PurchaseHistory si decides almacenarlo
                );
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return purchases;
    }
}
