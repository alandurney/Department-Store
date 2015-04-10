package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShopTableGateway {

    private final Connection mConnection;

    private static final String TABLE_NAME = "Shop";
    private static final String COLUMN_STOREID = "storeID";
    private static final String COLUMN_SHOPNAME = "shopName";
    private static final String COLUMN_MANFNAME = "manFNAme";
    private static final String COLUMN_MANLNAME = "manLName";
    private static final String COLUMN_PHONENO = "phoneNo";

    public ShopTableGateway(Connection connection) {
        mConnection = connection;
    }

    public int insertShop(String sn, String mf, String ml, int pn) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int id = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " ("
                //+ COLUMN_STOREID + ", "
                + COLUMN_SHOPNAME + ", "
                + COLUMN_MANFNAME + ", "
                + COLUMN_MANLNAME + ", "
                + COLUMN_PHONENO
                + ") VALUES (?, ?, ?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, sn);
        stmt.setString(2, mf);
        stmt.setString(3, ml);
        stmt.setInt(4, pn);

        // execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
        }

        // return the id assigned to the row in the database
        return id;
    }

    public boolean deleteShop(int id) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL DELETE statement with place holders for the id of the row to be remove from the database
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_STOREID + " = ?";

        // create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was deleted from the database
        return (numRowsAffected == 1);
    }

    public List<Shop> getShops() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Shop> shops;         // the java.util.List containing the Manager objects created for each row
        // in the result of the query the id of a manager

        ///NOTE: SETTING THE VARIABLES OF THE DATA TYPES//
        String shopName, manFName, manLName;
        int storeID, phoneNo;
        Shop s;                   // a Manager object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Manager object, which is inserted into an initially
        // empty ArrayList
        shops = new ArrayList<Shop>();
        while (rs.next()) {
            storeID = rs.getInt(COLUMN_STOREID);
            shopName = rs.getString(COLUMN_SHOPNAME);
            manFName = rs.getString(COLUMN_MANFNAME);
            manLName = rs.getString(COLUMN_MANLNAME);
            phoneNo = rs.getInt(COLUMN_PHONENO);

            s = new Shop(storeID, shopName, manFName, manLName, phoneNo);
            shops.add(s);
        }

        // return the list of Manager objects retrieved
        return shops;
    }

    boolean updateShop(Shop s) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_SHOPNAME + " = ?, "
                + COLUMN_MANFNAME + " = ?, "
                + COLUMN_MANLNAME + " = ? "
                + COLUMN_PHONENO + " = ? "
                + " WHERE " + COLUMN_STOREID + " = ?";

        // create a PreparedStatement object to execute the query and insert the new values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, s.getShopName());
        stmt.setString(2, s.getManFName());
        stmt.setString(3, s.getManLName());
        stmt.setInt(4, s.getPhoneNo());
        stmt.setInt(5, s.getStoreID());

        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return true if one and only one row was updated in the database
        return (numRowsAffected == 1);
    }

}
