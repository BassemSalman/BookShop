package com.project.bookshop.view;

import com.project.bookshop.model.Book;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public abstract class MainView {
    public abstract void display(HBox booksContainer, AnchorPane leftPane, String username);
    public abstract void renderBooks(HBox BooksContainer, String username);

    public abstract void renderBooks(ArrayList<Book> books, HBox BooksContainer, String username);
}
