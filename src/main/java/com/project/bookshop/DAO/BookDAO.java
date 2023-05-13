package com.project.bookshop.DAO;

import com.project.bookshop.DBProvider;
import com.project.bookshop.builder.BookBuilder;
import com.project.bookshop.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;
    public BookDAO() {
        try {
            this.connection =  DBProvider.getConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks()  {
        List<Book> books = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                Book book = new BookBuilder()
                        .withId(resultSet.getInt("id"))
                        .withGenreId(resultSet.getInt("genre_id"))
                        .withName(resultSet.getString("name"))
                        .withAuthor(resultSet.getString("author"))
                        .withPrice(resultSet.getInt("price"))
                        .withDescription(resultSet.getString("description"))
                        .withImage(resultSet.getBytes("image"))
                        .withQuantity(resultSet.getInt("quantity"))
                        .withIsDeleted(resultSet.getBoolean("is_deleted"))
                        .build();
                books.add(book);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getAllBooks(String search) throws SQLException {
        if(search.isEmpty()) return getAllBooks();
        List<Book> books = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE name LIKE '%" + search + "%'");

            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("id"));
                Book book = new BookBuilder()
                        .withId(resultSet.getInt("id"))
                        .withGenreId(resultSet.getInt("genre_id"))
                        .withName(resultSet.getString("name"))
                        .withAuthor(resultSet.getString("author"))
                        .withPrice(resultSet.getInt("price"))
                        .withDescription(resultSet.getString("description"))
                        .withImage(resultSet.getBytes("image"))
                        .withQuantity(resultSet.getInt("quantity"))
                        .withIsDeleted(resultSet.getBoolean("is_deleted"))
                        .build();
//                System.out.println(book);
                books.add(book);
            }
        }
        return books;
    }

    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (genre_id, name, author, price, description, image, quantity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getGenreId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.setDouble(4, book.getPrice());
            statement.setString(5, book.getDescription());
            statement.setBytes(6, book.getImage());
            statement.setInt(7, book.getQuantity());

            statement.executeUpdate();
        }
    }


    public void deleteBook(int bookId) throws SQLException {
        String sql = "UPDATE books SET is_deleted=1 WHERE id = " + bookId;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> getUserBooks(String username) {
        ArrayList<String> books = new ArrayList<String>();
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT b.name FROM purchases p " +
                    "JOIN users u ON u.id = p.user_id " +
                    "JOIN books b ON b.id = p.book_id " +
                    "WHERE u.name = '" + username + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String bookTitle = rs.getString("name");
                books.add(bookTitle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public Book getBookById(int bookId) {
        Book book = null;
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM books WHERE id = " + bookId;
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                book = new BookBuilder()
                        .withId(rs.getInt("id"))
                        .withGenreId(rs.getInt("genre_id"))
                        .withName(rs.getString("name"))
                        .withAuthor(rs.getString("author"))
                        .withPrice(rs.getInt("price"))
                        .withDescription(rs.getString("description"))
                        .withImage(rs.getBytes("image"))
                        .withQuantity(rs.getInt("quantity"))
                        .withIsDeleted(rs.getBoolean("is_deleted"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    public void updateBookQuantity(int bookId, int newQuantity) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "UPDATE books SET quantity = " + newQuantity + " WHERE id = " + bookId;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
