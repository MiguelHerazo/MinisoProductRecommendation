package org.example.model;

public class User {
    private int userId;
    private String email;
    private String name;

    public User(int userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    // Getters y Setters
    public int getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}
