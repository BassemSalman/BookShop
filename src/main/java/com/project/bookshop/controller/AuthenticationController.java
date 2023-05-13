package com.project.bookshop.controller;

import com.project.bookshop.DAO.UserDAO;
import com.project.bookshop.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class AuthenticationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField signUpPass;

    @FXML
    private PasswordField signUpPassConfirm;

    @FXML
    private PasswordField loginPass;

    @FXML
    private TextField usernameSignup;

    private UserDAO userDAO;

    private Stage stage;

    public AuthenticationController() throws Exception {
        this.userDAO = new UserDAO();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = loginPass.getText() ;
        Node n = (Node) actionEvent.getSource();
        stage = (Stage) n.getScene().getWindow();

        if(username == null || password == null){
            username = "";
            password = "";
        }

        if ((username.equals("admin") && password.equals("password")) || userDAO.login(username, password)) {
            try {
                Navigator.getInstance().navigateToMain(username);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            // Login failed, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
        }
        clearCredentials();
    }

    public void handleSignUpEvent(ActionEvent actionEvent) throws Exception{
        String username = usernameSignup.getText();
        String confirmPassword = signUpPassConfirm.getText();
        String password = signUpPass.getText();
        boolean flag = true;
        Node n = (Node) actionEvent.getSource();
        stage = (Stage) n.getScene().getWindow();

        if(username == null || password == null || confirmPassword==null){
            username = "";
            password = "";
            confirmPassword="";
        }

        if(confirmPassword.equals(password) && !(username.equals("admin"))) {
            if (userDAO.createUser(username, password)) {
                Navigator.getInstance().navigateToMain(username);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Signup Complete");
                alert.showAndWait();
                flag = false;
            }
        }
        if(flag == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SignUp Error");
                alert.setHeaderText(null);
                alert.setContentText("Credentials Error!.");
                alert.showAndWait();
            }
    }

    @FXML
    public void navigate(ActionEvent actionEvent) throws Exception { // goes to signup
        Node n = (Node) actionEvent.getSource();
        String path = "signup.fxml";
        String title = "Welcome New User!";
        Navigator.getInstance().navigate(path, title);
    }
    @FXML
    private void goBack(ActionEvent event) { // goes back to singin
        Navigator.getInstance().goBack(event);
    }

    // others navigateToMain providing it username


    public void clearCredentials(){
        usernameField.setText("");
        loginPass.setText("");
    }
}
