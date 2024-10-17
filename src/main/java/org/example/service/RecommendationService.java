package org.example.service;

import org.example.model.Purchase;
import org.example.model.Product;
import org.example.repository.PurchaseHistoryRepository;
import org.example.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {
    private PurchaseHistoryRepository purchaseHistoryRepository;
    private ProductRepository productRepository;

    public RecommendationService() {
        purchaseHistoryRepository = new PurchaseHistoryRepository();
        productRepository = new ProductRepository();
    }

    // Obtener recomendaciones basadas en el historial de compras
    public List<Product> getRecommendations(int userId) {
        List<Purchase> purchases = purchaseHistoryRepository.getPurchaseHistory(userId);
        List<Product> recommendations = new ArrayList<>();

        // Lógica para obtener recomendaciones basadas en el historial
        if (purchases.size() >= 2) {
            // Aquí puedes implementar la lógica de recomendación que desees
            // Por ejemplo, agregar productos comprados más de una vez
            for (Purchase purchase : purchases) {
                Product product = productRepository.getProductById(purchase.getProductId());
                recommendations.add(product);
            }
        }

        return recommendations;
    }
}
