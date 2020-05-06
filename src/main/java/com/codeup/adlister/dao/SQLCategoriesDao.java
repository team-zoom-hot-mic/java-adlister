package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import controllers.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCategoriesDao implements Categories {
    private Connection connection = null;

    public SQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(

                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()

            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public List<Category> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCatsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all categories.", e);
        }
    }

    @Override
    public Long insert(Category category) {
        return null;
    }

    @Override
    public Category singleCat(Long id) {
        return null;
    }

    private Category extractAd(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("id"),
                rs.getString("title")
        );
    }

    private List<Category> createCatsFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractAd(rs));
        }
        return categories;
    }
}
