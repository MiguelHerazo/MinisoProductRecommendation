package org.example.model;

import java.sql.Date;

public class Purchase {
    private int purchaseId; // ID de la compra
    private int userId; // ID del usuario que realizó la compra
    private int productId; // ID del producto comprado
    private Date purchaseDate; // Fecha de compra

    // Constructor para una nueva compra
    public Purchase(int userId, int productId, Date purchaseDate) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    // Constructor para una compra existente
    public Purchase(int purchaseId, int userId, int productId, Date purchaseDate) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    // Getters y setters
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
