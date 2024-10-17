package org.example.controller;

import org.example.repository.UserRepository;
import org.example.repository.ProductRepository;
import org.example.model.User;
import org.example.model.Product;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final Scanner scanner;

    public ConsoleController() {
        this.userRepository = new UserRepository();
        this.productRepository = new ProductRepository();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Elija una opción:");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Agregar producto");
            System.out.println("3. Mostrar usuarios");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el correo: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String name = scanner.nextLine();
                    userRepository.addUser(email, name);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del producto: ");
                    String productName = scanner.nextLine();
                    System.out.print("Ingrese la categoría: ");
                    String category = scanner.nextLine();
                    System.out.print("Ingrese el precio: ");
                    double price = scanner.nextDouble();
                    System.out.print("Ingrese el stock: ");
                    int stock = scanner.nextInt();
                    productRepository.addProduct(productName, category, price, stock);
                    break;
                case 3:
                    List<User> users = userRepository.getAllUsers();
                    System.out.println("Usuarios:");
                    for (User user : users) {
                        System.out.println("ID: " + user.getUserId() + ", Email: " + user.getEmail() + ", Nombre: " + user.getName());
                    }
                    break;
                case 4:
                    List<Product> products = productRepository.getAllProducts();
                    System.out.println("Productos:");
                    for (Product product : products) {
                        System.out.println("ID: " + product.getProductId() + ", Nombre: " + product.getName() + ", Categoría: " + product.getCategory() + ", Precio: " + product.getPrice() + ", Stock: " + product.getStock());
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, por favor intente de nuevo.");
            }
        }
    }
}
