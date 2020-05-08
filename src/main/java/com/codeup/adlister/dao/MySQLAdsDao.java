package com.codeup.adlister.dao;

import com.cedarsoftware.util.io.JsonWriter;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
import controllers.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description, multiCategories) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.setString(4, ad.getCategory());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }


    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("title"),
                rs.getString("description")

        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }


    // method to retrieve single ad
    public Ad singleAd(Long id) {

        try {
            String query = "Select * FROM ads where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            return createAdsFromResults(result).get(0);


        } catch (SQLException e) {
            throw new RuntimeException("Error getting ad.", e);
        }

    }

    // method to retrieve all ads specific to a certain id
    @Override
    public List<Ad> searchByUserID(Long userId) {

        try {
            String query = "SELECT * FROM ads WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, userId);
            ResultSet result = stmt.executeQuery();
            return createAdsFromResults(result);

        } catch (SQLException e) {
            throw new RuntimeException("Error finding an ad by id.", e);
        }
    }

    // method for editing ad
    public void editAd(String title, String description, Long id) {
        String query = "UPDATE ads SET title = ?, description = ? where id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setLong(3, id);


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating an ad.", e);
        }
    }

    // method for deleting ad
    public void deleteAd(long id) {
        String query = "DELETE FROM ads where id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting an ad.", e);
        }
    }
    @Override
    public List<Ad> searchAdByTitle(String searchName) {
        String searchAd =  "SELECT * FROM ads WHERE title LIKE '%" + searchName + "%'";
        try {
            PreparedStatement stmt = connection.prepareStatement(searchAd);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e){
            System.out.println("No ads found");
            throw new RuntimeException("Error retrieving ads.", e);
        }
    }
}
