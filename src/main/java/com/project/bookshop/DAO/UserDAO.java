package com.project.bookshop.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.bookshop.DBProvider;
import com.project.bookshop.model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDAO {

    private final Connection connection ;

    public UserDAO() throws  Exception{
        this.connection =  DBProvider.getConnection();
    }
    public boolean createUser(String username, String password) {
        try {
            Statement stmt = connection.createStatement() ;
            String query = "SELECT * FROM users WHERE name='" + username + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return false;
            } else {
                query = "INSERT INTO users (name, password, balance) VALUES ('" + username + "', '" + hashPassword(password) + "', '" + 150 + "')";
                int rowsAffected = stmt.executeUpdate(query);
                return rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean login(String name, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE name='" + name + "' AND password='" + hashPassword(password) + "'";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error logging in user: " + e.getMessage());
        }
        return false;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().substring(0,6);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }

    public int getUserBalance(String username) {
        int balance = 0;
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT balance FROM users WHERE name = '" + username + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                balance = rs.getInt("balance");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return balance;
    }

    public User getUserByName(String username) {
        User user = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM users WHERE name = '" + username + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("id");
                int balance = rs.getInt("balance");
                user = new User();
                user.setName(username);
                user.setBalance(balance);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUserBalance(int userId, int newBalance) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "UPDATE users SET balance = " + newBalance + " WHERE id = " + userId;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
