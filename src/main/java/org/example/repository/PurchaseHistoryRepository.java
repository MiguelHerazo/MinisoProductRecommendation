package org.example.repository;

import org.example.model.Purchase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseHistoryRepository {
    private final String url = "jdbc:mariadb://localhost:3306/MinisoDB"; //lo mismo que comente en ProductRepository Compa ves y lee alla que pereza escribir dos veces
    private final String user = "root"; // Cambar esto segun tu usuario de mariadb compai
    private final String password = "migue"; // Cambar esto segun tu usuario de mariadb compai

    // Metodo para establecer la conexión a la base de datos
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Registrar una nueva compra
    public void createPurchase(Purchase purchase) {
        String sql = "INSERT INTO PurchaseHistory (user_id, product_id, purchase_date) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, purchase.getUserId());
            pstmt.setInt(2, purchase.getProductId());
            pstmt.setDate(3, new java.sql.Date(purchase.getPurchaseDate().getTime()));
            pstmt.executeUpdate();
            System.out.println("Compra registrada para el usuario ID: " + purchase.getUserId());
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
                        rs.getDate("purchase_date")
                );
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return purchases;
    }
}
