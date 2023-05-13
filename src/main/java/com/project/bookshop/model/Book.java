package com.project.bookshop.model;

import java.sql.Blob;

public class Book {
    private int id;
    private int genreId;
    private String name;
    private String author;
    private int price;
    private String description;
    private byte[] image;
    private int quantity;

    private boolean isDeleted;

    public Book(int id, int genreId, String name, String author, int price, String description, byte[] image, int quantity, boolean isDeleted) {
        this.id=id;
        this.genreId = genreId;
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", genreId=" + genreId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
