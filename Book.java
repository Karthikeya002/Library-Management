package Lib;

import java.sql.*;

public class Book {
    public static void addBook(String title, String author) {
        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO books (title, author) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listBooks() {
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM books WHERE available = TRUE";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("title") + " | " +
                                   rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
