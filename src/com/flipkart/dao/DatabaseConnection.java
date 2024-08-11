package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    
    private static final String URL = "jdbc:mysql://127.0.0.1:4000/CRS_JEDI";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

   
    public static Connection getConnection() {
        Connection con = null;
        try {
           
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return con;
    }

    
    public static void main(String[] args) {
        Connection con = getConnection();
        if (con != null) {
            System.out.println("Connection established successfully.");
        } else {
            System.out.println("Failed to establish connection.");
        }
    }
}
