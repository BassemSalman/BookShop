package com.project.bookshop.view;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.DAO.UserDAO;
import com.project.bookshop.Navigator;
import com.project.bookshop.model.Book;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class MainViewUser extends MainView {
    Label balanceLabel;

    public void display(HBox booksContainer, AnchorPane leftPane, String username) { // booksContainer unused
        int balance = 0;
        try {
            balance = new UserDAO().getUserBalance(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        balanceLabel = new Label("Balance");
        balanceLabel.setLayoutX(18.0);
        balanceLabel.setLayoutY(150);
        balanceLabel.setPrefHeight(26.0);
        balanceLabel.setPrefWidth(94.0);
        balanceLabel.setTextFill(Color.web("#6600ff"));
        balanceLabel.setFont(Font.font("System Bold", 12.0));
        balanceLabel.setId("#balanceLabel");
        balanceLabel.setText("Balance: " + balance);

        Button myBooksButton = new Button("My Books");
        myBooksButton.setLayoutX(14.0);
        myBooksButton.setLayoutY(64.0);
        myBooksButton.setStyle("-fx-background-color: blue;");
        myBooksButton.setTextFill(Color.WHITE);
        myBooksButton.setTextAlignment(TextAlignment.CENTER);
        myBooksButton.setFont(Font.font("System Bold", 12));

        myBooksButton.setOnAction(event -> {
            try {
                Navigator.navigateToMyBooks(username);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        leftPane.getChildren().addAll(myBooksButton, balanceLabel);
    }

    public void renderBooks(HBox booksContainer, String username) {
        ArrayList<Book> books = (ArrayList<Book>) new BookDAO().getAllBooks();
        BookContainerUser bookContainerUser;

        for (Book b : books) {
            if (!b.getIsDeleted() && b.getQuantity() > 0) {
                bookContainerUser = new BookContainerUser(b, username, balanceLabel);
                booksContainer.getChildren().add(bookContainerUser);
            }
        }
    }

    public void renderBooks(ArrayList<Book> books, HBox booksContainer, String username) {
        BookContainerUser bookContainerUser;

        for (Book b : books) {
            if (!b.getIsDeleted() && b.getQuantity() > 0) {
                bookContainerUser = new BookContainerUser(b, username, balanceLabel);
                booksContainer.getChildren().add(bookContainerUser);
            }
        }
    }
}