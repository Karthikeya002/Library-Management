package Lib;

import java.sql.*;

public class Transaction{
    public static void borrowBook(int userId, int bookId) {
        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO transactions (user_id, book_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();

            query = "UPDATE books SET available = FALSE WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();

            System.out.println("Book borrowed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void returnBook(int bookId) {
        try (Connection conn = Database.getConnection()) {
            String query = "UPDATE transactions SET returned = TRUE WHERE book_id = ? AND returned = FALSE";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();

            query = "UPDATE books SET available = TRUE WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();

            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
