package com.project.bookshop.view;

import com.project.bookshop.model.Book;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainViewProxy {
    String username;
    MainView mainView;

    public MainViewProxy(String username) {
        if(username == null) System.out.println("oops");
        this.username = username;
    }
    public void display(HBox booksContainer, AnchorPane leftPane, String username)  {
        if(authorized()){
            mainView = new MainViewAdmin();
        }
        else {
            mainView = new MainViewUser();
        }
        mainView.display(booksContainer, leftPane, username); // username needed for myBooks
        mainView.renderBooks(booksContainer, username); // username needed for Description
    }

    public void renderBooks(HBox booksContainer, String username){
        if(authorized()){
            mainView = new MainViewAdmin();
        }
        else {
            mainView = new MainViewUser();
        }
        mainView.renderBooks(booksContainer, username);
    }
    public void renderBooks(ArrayList<Book> books, HBox booksContainer, String username){
        if(authorized()){
            mainView = new MainViewAdmin();
        }
        else {
            mainView = new MainViewUser();
        }
        mainView.renderBooks(books, booksContainer, username);
    }

    public boolean authorized() {
        return "admin".equals(this.username);
    }

}