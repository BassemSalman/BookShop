package com.project.bookshop.builder;

import com.project.bookshop.model.Book;

public class BookBuilder {
    private int id;
    private int genreId;
    private String name;
    private String author;
    private int price;
    private String description;
    private byte[] image;
    private int quantity;
    private boolean isDeleted;

    public BookBuilder withId(int id) {
        this.id = id;
        return this;
    }
    public BookBuilder withIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public BookBuilder withGenreId(int genreId) {
        this.genreId = genreId;
        return this;
    }

    public BookBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BookBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public BookBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BookBuilder withImage(byte[] image) {
        this.image = image;
        return this;
    }

    public BookBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Book build() {
        return new Book(id, genreId, name, author, price, description, image, quantity, isDeleted);
    }
}
