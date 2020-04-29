package models;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContactsDao implements Contacts {
    // Set up the DB connection
    private Connection conn;

    public MySQLContactsDao() throws SQLException {
        // Instantiate a Config object
        Config config = new Config();

        // Set up our database driver, and make a connection
        DriverManager.registerDriver(new Driver());

        // Get a connection object
        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword() // see, no passwords in plain text!
        ); // we now have a connection to our MySQL DB
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> output = new ArrayList<>();

        // Query the SQL DB table for all contacts
        String query = "SELECT * FROM contacts";

        // Take into account the SQL Exception that needs to be handled
        try {
            // Create a statement object
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Iterate through our result set and add each contact to our 'Contact' Bean
            while(rs.next()) {
                output.add(
                        new Contact(
                                rs.getLong("id"), // id
                                rs.getString("first_name"), // first name
                                rs.getString("last_name"), // last name
                                rs.getString("phone_number") // phone number
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output; // returns the List<Contact> of all the contacts we imported from MySQL DB

    }

    @Override
    public long saveContact(Contact contact) {
        // a Contact object sent in with first/last/phone,
            // we need to insert that info into a new row in the database, then return the generated id (Primary Key)
        // Instantiate a new Contact Object

        // INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('casey', 'friday', '2105557777');

        // This is the id we'll return after inserting the user into the DB table
        long newlyCreatedUserId = 0;

        String addContactQuery = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber()
        );
        try {
            Statement stmt = conn.createStatement();
            // NOW let's actually execute this SQL query to add the new contact to our database
            stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);
            // If we add Statement.RETURN_GENERATED_KEYS, we can work with the actual MySQL DB table row ID's, and reassign those ID's to our Contact objects here in our Java code

            ResultSet ks = stmt.getGeneratedKeys();
            if(ks.next()) {
                newlyCreatedUserId = ks.getLong(1); // This will save the MySQL row ID to a variable
            }
            // Check to see if the id was returned, or if insertedRowId is still at its default of '0'
            if( newlyCreatedUserId != 0 ) {
                contact.setId(newlyCreatedUserId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // return the newly inserted user's id
        return newlyCreatedUserId;

    }

    @Override
    public void deleteContactById(long id) {
        // 'id' passed in is the id of the row we want to delete from our DB table

        // SQL equiv for deleting row by id: DELETE FROM contacts WHERE id = sentInId
        String query = String.format("DELETE FROM contacts WHERE id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            boolean success = stmt.execute(query);
            if(success) {
                System.out.println("Contact has been deleted.");
            } else {
                System.out.println("Contact was not deleted - check for syntax errors.");
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact getContactById(long id) {

        Contact returnContact = new Contact();
        String query = String.format("SELECT * FROM contacts WHERE id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()) { // this means we found a match!
                returnContact.setId(id);
                returnContact.setFirstName(rs.getString("first_name"));
                returnContact.setLastName(rs.getString("last_name"));
                returnContact.setPhoneNumber(rs.getString("phone_number"));
            } else { // there was no match for the id that was sent in
                System.out.println("Supplied user id found no contact matches.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnContact;
    }
}
