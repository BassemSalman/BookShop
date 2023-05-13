package com.project.bookshop.model;

import java.time.LocalDateTime;

public class Purchase {
    private int id;
    private int userId;
    private int bookId;


    public Purchase(int id, int userId, int bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
