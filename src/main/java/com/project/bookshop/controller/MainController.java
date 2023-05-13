package com.project.bookshop.controller;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.DAO.UserDAO;
import com.project.bookshop.view.MainView;
import com.project.bookshop.view.MainViewProxy;
import com.project.bookshop.Navigator;
import com.project.bookshop.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {
    @FXML
    private Label usernameLabel;
    @FXML
    private HBox booksContainer;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private TextField searchfield;
    private UserDAO userDAO;
    private String username;

    private Stage stage;



    public MainController() {
//        System.out.println("construct");

        try {
            this.userDAO = new UserDAO();
        } catch (Exception s) {
            s.printStackTrace();
        }
    }
    public void setUsername(String username) throws Exception { this.username = username; initialize();}

    @FXML
    private void goBack(ActionEvent event) {
        Navigator.getInstance().goBack(event);
    }

    // proxy internally decides what view to show
    @FXML
    public void initialize() throws Exception {
        // fxml is initialized now
        if(username == null) return;
        ArrayList<Book> books = (ArrayList<Book>) new BookDAO().getAllBooks();
        usernameLabel.setText(username);
        MainViewProxy view = new MainViewProxy(username);
        if(booksContainer == null) return;
        view.display(booksContainer, leftPane, username); //calls renderBooks of subsequent type
    }


    public void handleSearch(ActionEvent actionEvent) throws Exception {
        ArrayList<Book> books = (ArrayList<Book>) new BookDAO().getAllBooks(searchfield.getText());
        MainViewProxy view = new MainViewProxy(username);
        view.renderBooks(books, booksContainer, username);
    }
}

