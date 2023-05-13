package com.project.bookshop.controller;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.Navigator;
import com.project.bookshop.model.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MyBooksController {
    String username;

    @FXML
    ListView listofnames;

    public void setUsername(String username) {
        this.username=username;
        try{
            initialize();
        }
        catch(Exception e){
            return;
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        Navigator.getInstance().goBack(event);
    }

    @FXML
    public void initialize() throws Exception {
        if(username == null) return;
        ArrayList<String> myBooks = new BookDAO().getUserBooks(username);
//        for(String s : myBooks) System.out.println("titke:" + s);
        listofnames.getItems().addAll(myBooks);
    }
}
