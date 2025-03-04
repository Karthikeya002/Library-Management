package Lib;

import java.sql.*;

public class User {
    public static void registerUser(String name, String email) {
        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

