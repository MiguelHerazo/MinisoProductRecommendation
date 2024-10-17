package org.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseRepository {
    private final String url = "jdbc:mariadb://localhost:3316/MinisoDB"; // Cambiar según configuración
    private final String user = "root"; // Cambiar según tu usuario de MariaDB
    private final String password = "migue"; // Cambiar según tu contraseña de MariaDB

    // Método para establecer la conexión a la base de datos
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Registrar una nueva compra
    public void addPurchase(int userId, int productId) {
        String sql = "INSERT INTO Purchase (user_id, product_id, purchase_date) VALUES (?, ?, NOW())";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("Compra registrada con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
