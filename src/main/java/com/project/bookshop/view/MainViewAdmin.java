package com.project.bookshop.view;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.Navigator;
import com.project.bookshop.model.Book;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainViewAdmin extends MainView {
    public void display(HBox booksContainer, AnchorPane leftPane, String username) { // username unused here
        Button addButton = new Button("Add Book");
        addButton.setLayoutX(14.0);
        addButton.setLayoutY(64.0);
        addButton.setStyle("-fx-background-color: blue;");
        addButton.setTextFill(Color.WHITE);
        addButton.setTextAlignment(TextAlignment.CENTER);
        addButton.setFont(Font.font("System Bold", 12));
        addButton.setOnAction(event -> {
            try {
                Navigator.navigateToAddBook(booksContainer, this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        leftPane.getChildren().add(addButton);
    }

    // addBooks needs Hbox and username :P
    public void renderBooks(HBox booksContainer, String username){
        ArrayList<Book> books = (ArrayList<Book>) new BookDAO().getAllBooks();
        booksContainer.getChildren().clear();

        BookContainerAdmin bookContainerAdmin;
        for(Book b : books) {
            bookContainerAdmin = new BookContainerAdmin(b);
            booksContainer.getChildren().add(bookContainerAdmin);
        }
    }

    public void renderBooks(ArrayList<Book> books, HBox booksContainer, String username){
        booksContainer.getChildren().clear();
        BookContainerAdmin bookContainerAdmin;
        for(Book b : books) {
            bookContainerAdmin = new BookContainerAdmin(b);
            booksContainer.getChildren().add(bookContainerAdmin);
        }
    }
}