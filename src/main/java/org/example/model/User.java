package org.example.model;

public class User {
    private int userId; // ID del usuario (auto-incremental en la base de datos)
    private String name; // Nombre del usuario
    private String email; // Correo del usuario

    // Constructor para crear un nuevo usuario (con auto-incremento)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Constructor para obtener un usuario existente de la base de datos (con ID)
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getters y setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
