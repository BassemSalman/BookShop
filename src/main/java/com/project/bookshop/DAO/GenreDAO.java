package com.project.bookshop.DAO;

import com.project.bookshop.DBProvider;
import com.project.bookshop.model.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.project.bookshop.DBProvider.getConnection;

public class GenreDAO {
    private final Connection connection ;

    public GenreDAO() throws Exception {
        this.connection =  getConnection();
    }

    public int getGenreByName(String name) throws SQLException {
        Statement stmt;
        ResultSet rs;
        int genreId = -1;

        try {
            stmt = connection.createStatement();
            String query = "SELECT id FROM genres WHERE name = '" + name + "'";
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                genreId = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return genreId;
    }

    public List<Genre> getAllGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM genres");

            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );

                genres.add(genre);
            }
        }

        return genres;
    }

    public String getGenreById(int genreId) {
        Genre genre = null;
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM genres WHERE id = " + genreId;
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                genre = new Genre(rs.getInt("id"), rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return genre.getName();
    }

}
