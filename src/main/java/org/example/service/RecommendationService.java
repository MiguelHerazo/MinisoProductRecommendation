package org.example.service;

import org.example.model.Product;
import org.example.model.Purchase;
import org.example.repository.ProductRepository;
import org.example.repository.PurchaseHistoryRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendationService {
    private final ProductRepository productRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    public RecommendationService() {
        this.productRepository = new ProductRepository();
        this.purchaseHistoryRepository = new PurchaseHistoryRepository();
    }

    // Método para obtener recomendaciones basadas en compras anteriores
    public List<Product> getRecommendations(int userId) {
        // Obtener el historial de compras del usuario
        List<Purchase> purchases = purchaseHistoryRepository.getPurchaseHistory(userId);

        // Mapa para contar la frecuencia de productos comprados
        Map<Integer, Integer> productFrequency = new HashMap<>();

        // Contar la frecuencia de cada producto en el historial de compras
        for (Purchase purchase : purchases) {
            productFrequency.put(purchase.getProductId(),
                    productFrequency.getOrDefault(purchase.getProductId(), 0) + 1);
        }

        // Filtrar productos que se compraron al menos 2 veces
        List<Integer> frequentProductIds = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : productFrequency.entrySet()) {
            if (entry.getValue() >= 2) {
                frequentProductIds.add(entry.getKey());
            }
        }

        // Obtener todos los productos
        List<Product> allProducts = productRepository.getAllProducts();
        List<Product> recommendations = new ArrayList<>();

        // Agregar productos recomendados (excluyendo los ya comprados)
        for (Product product : allProducts) {
            if (!frequentProductIds.contains(product.getProductId())) {
                recommendations.add(product);
            }
        }

        return recommendations;
    }
}
