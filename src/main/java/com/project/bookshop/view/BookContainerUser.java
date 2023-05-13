package com.project.bookshop.view;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.Navigator;
import com.project.bookshop.model.Book;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLOutput;

public class BookContainerUser extends VBox {
    private ImageView image;
    private Image photo;

    private Text bookTitle;
    private Button descriptionButton;
    private String username;
    private int id;
    public BookContainerUser(Book book, String username, Label balanceLabel) {
        byte[] imageData = book.getImage();

        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);

        photo = new Image(bis);
        image = new ImageView();
        image.setImage(photo);
        image.setFitHeight(100);
        image.setFitWidth(100);

        bookTitle = new Text(book.getName());
        bookTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        setSpacing(10);
        setPadding(new Insets(10));

        id =  book.getId();
        descriptionButton = new Button("Description");

        descriptionButton.setOnAction(event -> {
            try {
                Navigator.getInstance().navigateToDescription(book.getId(), username, balanceLabel);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        setSpacing(10);
        setPadding(new Insets(10));
        String priceString = "Price : " + book.getPrice();
        Text price = new Text(priceString);
        getChildren().addAll(image, bookTitle, descriptionButton, price);
    }
}
