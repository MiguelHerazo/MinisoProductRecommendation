package org.example.controller;

import org.example.model.Product;
import org.example.model.User;
import org.example.model.Purchase;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import org.example.repository.PurchaseHistoryRepository;
import org.example.repository.PurchaseRepository;
import org.example.service.RecommendationService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final Scanner scanner;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final RecommendationService recommendationService;

    public ConsoleController() {
        this.scanner = new Scanner(System.in);
        this.userRepository = new UserRepository();
        this.productRepository = new ProductRepository();
        this.purchaseRepository = new PurchaseRepository();
        this.purchaseHistoryRepository = new PurchaseHistoryRepository();
        this.recommendationService = new RecommendationService();
    }

    public void start() {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Agregar producto");
            System.out.println("3. Mostrar productos");
            System.out.println("4. Realizar compra");
            System.out.println("5. Ver historial de compras");
            System.out.println("6. Ver recomendaciones de productos");
            System.out.println("7. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    showProducts();
                    break;
                case 4:
                    makePurchase();
                    break;
                case 5:
                    viewPurchaseHistory();
                    break;
                case 6:
                    showRecommendations();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void addUser() {
        System.out.print("Ingrese el nombre del usuario: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el correo del usuario: ");
        String email = scanner.nextLine();

        // Crear el usuario sin el ID, que será auto-incremental
        User user = new User(name, email.trim().toLowerCase()); // Normalizando el correo
        userRepository.addUser(user);
    }

    private void addProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la categoría del producto: ");
        String category = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double price = scanner.nextDouble();
        System.out.print("Ingrese la cantidad en stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea restante

        // Crear el producto sin el ID, que será auto-incremental
        Product product = new Product(name, category, price, stock);
        productRepository.addProduct(product);
    }


    private void showProducts() {
        List<Product> products = productRepository.getAllProducts();
        System.out.println("Productos disponibles:");
        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ", Nombre: " + product.getName() + ", Categoría: " + product.getCategory() + ", Precio: " + product.getPrice() + ", Stock: " + product.getStock());
        }
    }

    private void makePurchase() {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = scanner.nextInt();
        System.out.print("Ingrese el ID del producto: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Crear un objeto Purchase sin la fecha
        // O simplemente llamar al metodo directamente con los parámetros
        purchaseRepository.addPurchase(userId, productId);
    }

    private void viewPurchaseHistory() {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        List<Purchase> historial = purchaseHistoryRepository.getPurchaseHistory(userId);

        if (historial.isEmpty()) {
            System.out.println("No hay historial de compras para el usuario ID: " + userId);
        } else {
            System.out.println("Historial de compras para el usuario ID: " + userId);
            for (Purchase purchase : historial) {
                System.out.println("Compra ID: " + purchase.getPurchaseId() +
                        ", Producto ID: " + purchase.getProductId() +
                        ", Fecha: " + purchase.getPurchaseDate());
            }
        }
    }

    private void showRecommendations() {
        System.out.print("Ingrese el ID del usuario: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        List<Product> recommendations = recommendationService.getRecommendations(userId);
        System.out.println("Recomendaciones de productos:");
        for (Product product : recommendations) {
            System.out.println("ID: " + product.getId() + ", Nombre: " + product.getName() + ", Precio: " + product.getPrice());
        }
    }
}
