package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQL {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver loaded successfully!");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/projetjee", "root", "112233");
            System.out.println("✅ Connection established successfully!");

            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ SQL error while connecting!");
            e.printStackTrace();
        }
    }
}


