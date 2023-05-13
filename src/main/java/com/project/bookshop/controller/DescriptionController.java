package com.project.bookshop.controller;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.DAO.GenreDAO;
import com.project.bookshop.DAO.PurchaseDAO;
import com.project.bookshop.DAO.UserDAO;
import com.project.bookshop.Navigator;
import com.project.bookshop.model.Book;
import com.project.bookshop.model.Purchase;
import com.project.bookshop.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

public class DescriptionController {

    private int bookId;
    private String username;
    private int balance;
    private int price;
    @FXML
    private ImageView bookImage;
    @FXML
    private Label bookNameLabel;

    @FXML
    private Label bookAuthorLabel;

    @FXML
    private Label bookPriceLabel;

    @FXML
    private Label bookGenreLabel;

    @FXML
    private TextArea bookDescriptionTextArea;

    private Label balanceLabel;
    public void setters(int id, String username, Label balanceLabel) {
        this.username=username;
        this.bookId=id;
        this.balanceLabel = balanceLabel;

        try {
            this.initialize();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void goBack(ActionEvent event) {
        Navigator.getInstance().goBack(event);
    }

    public void initialize() throws SQLException {
        // display book details here
        Book book = new Book();
        if(bookId == 0 || username==null) return; // not set yet by navigator
        try{
            book = new BookDAO().getBookById(bookId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String genre="";
        try{
            genre = String.valueOf(new GenreDAO().getGenreById(book.getGenreId()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(book.getImage());
        bookImage.setImage(new Image(bis));
        bookNameLabel.setText(book.getName());
        bookAuthorLabel.setText("Author: " + book.getAuthor());
        bookPriceLabel.setText("Price: $" + book.getPrice());
        bookGenreLabel.setText("Genre: " + genre);
        bookDescriptionTextArea.setText(book.getDescription());
    }


    public void handleBuyButtonAction(ActionEvent actionEvent) throws Exception {
        User u = new UserDAO().getUserByName(username);
        Book b = new Book();
        try {
             b = new BookDAO().getBookById(bookId);
        }catch(Exception e){
            e.printStackTrace();
        }
        balance = u.getBalance();
        price = b.getPrice();

        if(balance >= price) {
            new UserDAO().updateUserBalance(u.getId(), balance-price);
            new BookDAO().updateBookQuantity(bookId, b.getQuantity()-1);
            Purchase p = new Purchase(0, u.getId(), bookId);
            new PurchaseDAO().purchaseBook(p);

            System.out.println("Updating balance");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Transaction Successful");
            alert.setHeaderText("Your purchase has been successful.");
            alert.setContentText("Thank you for your purchase.");
            alert.showAndWait();
            balanceLabel.setText("Balance: "+ (balance-price));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Insufficient Funds");
            alert.setHeaderText("You have insufficient funds to make this purchase.");
            alert.setContentText("Please add funds to your account and try again.");
            alert.showAndWait();
        }
    }
}
