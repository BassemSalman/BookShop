package com.project.bookshop.view;

import com.project.bookshop.DAO.BookDAO;
import com.project.bookshop.model.Book;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;

public class BookContainerAdmin extends VBox {
    private ImageView image;
    private Image photo;

    private Text bookTitle;
    private Button deleteButton;
    private int id;
    private InputStream inputStream;
    public BookContainerAdmin(Book book) {
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

        if(image != null) System.out.println(image);
        if(photo != null) System.out.println(photo);
        if(inputStream != null) System.out.println(inputStream);
        if(imageData != null) System.out.println(imageData);

        id =  book.getId();
        deleteButton = new Button("Delete");
        if(book.getIsDeleted()){
            deleteButton.setText("((Deleted))");
        }

        deleteButton.setOnAction(event -> {
            try {
                new BookDAO().deleteBook(id);
                deleteButton.setText("((Deleted))");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        setSpacing(10);
        setPadding(new Insets(10));

        String quantityString = "Quantity : " + book.getQuantity();
        Text quantity = new Text(quantityString);
        getChildren().addAll(image, bookTitle, deleteButton, quantity);

    }
}
