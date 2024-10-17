package org.example.model;

import java.util.Date;

public class Purchase {
    private int purchaseId;
    private int userId;
    private int productId;
    private Date purchaseDate;

    public Purchase(int purchaseId, int userId, int productId, Date purchaseDate) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
    }

    // Getters y Setters
    public int getPurchaseId() { return purchaseId; }
    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public Date getPurchaseDate() { return purchaseDate; }
}
