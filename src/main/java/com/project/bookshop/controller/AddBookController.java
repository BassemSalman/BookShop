package com.project.bookshop.controller;
import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.DAO.GenreDAO;
import com.project.bookshop.Navigator;
import com.project.bookshop.builder.BookBuilder;
import com.project.bookshop.model.Book;
import com.project.bookshop.model.Genre;
import com.project.bookshop.view.MainViewAdmin;
import com.project.bookshop.view.MainViewProxy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddBookController {
    @FXML
    private TextField nameText;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField authorText;
    @FXML
    private TextField quantityText;
    @FXML
    private TextField priceText;
    @FXML
    private ChoiceBox<String> genreChoice;
//    private File imageFile;
//    private String imageName;
    @FXML
    private void goBack(ActionEvent event) {
        Navigator.getInstance().goBack(event);
    }
    private byte[] imageData;
    private MainViewAdmin mainViewAdmin;
    private HBox booksContainer;

    public void setters(HBox booksContainer, MainViewAdmin mainViewAdmin){
        System.out.println("set done");
        this.booksContainer=booksContainer;
        this.mainViewAdmin=mainViewAdmin;
        initialize();
    }
    public void initialize() {
        if(booksContainer == null) return;
        ArrayList<Genre> genres ;
        try {
            genres = (ArrayList<Genre>) new GenreDAO().getAllGenres();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(Genre g : genres) {
            genreChoice.getItems().add(g.getName());
        }
    }


    @FXML
    public void handleSubmit(ActionEvent actionEvent) throws Exception {
        if (nameText.getText().isEmpty() || descriptionText.getText().isEmpty() || authorText.getText().isEmpty() || quantityText.getText().isEmpty()  || priceText.getText().isEmpty() || genreChoice.getSelectionModel().isEmpty() || imageData == null){ //imageName == null || imageFile==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("All fields are required");
            alert.setContentText("Please fill in all fields and upload an image.");
            alert.showAndWait();
            return;
        }
        int genreId = new GenreDAO().getGenreByName(String.valueOf(genreChoice.getValue()));
        Book book = new BookBuilder()
                .withName(nameText.getText())
                .withDescription(descriptionText.getText())
                .withAuthor(authorText.getText())
                .withPrice(Integer.parseInt(priceText.getText()))
                .withQuantity(Integer.parseInt(quantityText.getText()))
                .withImage(imageData)
                .withGenreId(genreId)
                .build();

        new BookDAO().addBook(book);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Book added successfully");
        alert.showAndWait();

        mainViewAdmin.renderBooks(booksContainer, "admin");
        Navigator.getInstance().goBack(actionEvent);
        clearForm();
    }

//    public static void uploadImageToResource(String imageName, File imageFile) {
//        String resourcePath = "src/main/resources/com/project/bookshop/images/";
//        Path imagePath = Paths.get(resourcePath + imageName);
//        try{
//            Files.createDirectories(imagePath.getParent());
//            Files.copy(imageFile.toPath(), imagePath);
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//    }


    @FXML
    public void handleUploadImage(ActionEvent event) throws IOException, SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imageData = Files.readAllBytes(selectedFile.toPath()); // now imageData contains bytes of image
        }
        else {
            System.out.println("selected file is null");
        }
    }

    private void clearForm() {
        nameText.setText("");
        descriptionText.setText("");
        authorText.setText("");
        quantityText.setText("");
        priceText.setText("");
        genreChoice.setValue(null);
        imageData=null;
    }


}

