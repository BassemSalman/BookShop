package com.project.bookshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;


public class BookStore extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // used in all app
        Navigator.getInstance().setStage(primaryStage);
        Navigator.getInstance().navigate("login.fxml", "Log In");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }


}