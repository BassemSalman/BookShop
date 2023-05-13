package com.project.bookshop.DAO;


import com.project.bookshop.DBProvider;
import com.project.bookshop.builder.BookBuilder;
import com.project.bookshop.model.Book;
import com.project.bookshop.model.Purchase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    private final Connection connection ;

    public PurchaseDAO() throws Exception {
        this.connection =  DBProvider.getConnection();
    }
    public void purchaseBook(Purchase p) throws SQLException {
        String sql = "INSERT INTO purchases (user_id, book_id) VALUES " +
                "(" + p.getUserId() + ", " + p.getBookId() + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

}

