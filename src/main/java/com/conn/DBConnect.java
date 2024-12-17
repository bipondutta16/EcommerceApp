package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    private static final Logger LOGGER = Logger.getLogger(DBConnect.class.getName());
    private static Connection conn = null;
    private static final String DB_URL = "jdbc:sqlite:C:/Users/ankit/eclipse-workspace/EcommerceApp/EcommerceApp/mydatabase.db"; // Make sure to use a relative path in production.

    // Private constructor to prevent instantiation
    private DBConnect() {}

    // Get the connection instance
    public static Connection getConn() {
        if (conn == null) {
            try {
                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(DB_URL);
                LOGGER.info("Database connection established successfully.");
            } catch (SQLException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Database connection failed.", e);
            }
        }
        return conn;
    }

    // Close the connection (useful for cleanup)
    public static void closeConn() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;  // Clear the connection object
                LOGGER.info("Database connection closed.");
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Failed to close database connection.", e);
            }
        }
    }
}
