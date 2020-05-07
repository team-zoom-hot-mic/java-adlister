package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import controllers.Config;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchAd {
    private Connection connection = null;

    public SearchAd(Config config) {
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

    public List<Ad> searchUserByName(String searchName) throws SQLException {
        String insertQuery = "SELECT * from ADS where TITLE like '%" + searchName + "%'";
        List<Ad> ads = new ArrayList<Ad>();
        PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();

        // Execute sql,
        // open ResultSet,
        // iterate it and for every ResultRow's row convert to object Ad
        // Add converted object Ad to Ads
        return ads;
    }
}
