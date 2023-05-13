package com.project.bookshop;

import com.project.bookshop.controller.AddBookController;
import com.project.bookshop.controller.DescriptionController;
import com.project.bookshop.controller.MainController;
import com.project.bookshop.controller.MyBooksController;
import com.project.bookshop.view.MainViewAdmin;
import com.project.bookshop.view.MainViewProxy;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;


public class Navigator {
    private static Stack<Scene> sceneStack;
    private static Navigator instance;
    private static Stage stage;
    private static MainViewProxy mainViewProxy;

    public static void setStage(Stage thestage){
        stage = thestage;
    }
    private Navigator() throws IOException {
        sceneStack = new Stack<>();
    }

    public static Navigator getInstance()  {
        if(instance == null){
            try{
                instance = new Navigator();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void goBack(ActionEvent event) {
        if (!sceneStack.isEmpty()) {
            sceneStack.pop(); // pop current scene
            if(!sceneStack.isEmpty()){
                Scene previousScene = sceneStack.peek();
                stage.setScene(previousScene);
            }
        }
        else {
            System.out.println("empty stack");
        }
    }

    public void navigateToDescription(int id, String username, Label balanceLabel) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("description.fxml"));
        Parent root = loader.load();
        DescriptionController descriptionController = loader.getController();
        Scene mainScene = sceneStack.peek();
        if(balanceLabel == null) return;

        descriptionController.setters(id, username, balanceLabel);
        Scene scene = new Scene(root);
        sceneStack.push(scene);
        stage.setTitle("Book Description");
        stage.setScene(scene);
    }


    public static void navigateToMyBooks(String username) throws Exception {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("mybooks.fxml"));
        Parent root = loader.load();
        ((MyBooksController)(loader.getController())).setUsername(username);
        Scene scene = new Scene(root);
        sceneStack.push(scene);
        stage.setTitle("My Books");
        stage.setScene(scene);
    }

    public static void navigateToAddBook(HBox booksContainer, MainViewAdmin mainViewAdmin) {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("addbook.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ((AddBookController)(loader.getController())).setters(booksContainer, mainViewAdmin);
        Scene scene = new Scene(root);
        sceneStack.push(scene);
        stage.setTitle("Add Books");
        stage.setScene(scene);
    }

    public static void navigate(String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(path));
        Parent root = loader.load();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        sceneStack.push(scene);
        stage.setScene(scene);
    }


    // no matter if admin or not, this is called
    public static void navigateToMain(String username) throws Exception {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("main.fxml"));
        Parent root = loader.load();
        MainController mc = (MainController) loader.getController();
        mc.setUsername(username);
        Scene scene = new Scene(root);
        sceneStack.push(scene);
        stage.setTitle("Main Page");
        stage.setScene(scene);
    }

    // upload back to main should refresh the hbox
    // addtobooks controller to have reference to l batikh w y3uz el method display?

    // balanceLabel rayha jey kenit


}