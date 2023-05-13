package com.project.bookshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBProvider {
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/book_store";
        String username = "root";
        String password = "password";
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Database ERROR");
        }
        return conn;
    }
}
