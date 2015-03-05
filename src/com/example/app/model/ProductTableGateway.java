package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductTableGateway {

    private final Connection mConnection;

    private static final String TABLE_NAME = "product";
    private static final String COLUMN_PRODUCT_ID = "productID";
    private static final String COLUMN_PROD_NAME = "prodName";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SALE_PRICE = "salePrice";
    private static final String COLUMN_SHOPID = "shopID";

    public ProductTableGateway(Connection connection) {
        mConnection = connection;
    }

    //this is a comment

    public List<Product> getProducts() throws SQLException {
        String query;
        Statement stmt;
        ResultSet rs;
        List<Product> products;

        //VARIABLES FOR EACH COLUMN
        String prodName, description;
        int productID, shopID;
        double price, salePrice;

        Product p;

        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        products = new ArrayList<Product>();
        while (rs.next()) {
            productID = rs.getInt(COLUMN_PRODUCT_ID);
            prodName = rs.getString(COLUMN_PROD_NAME);
            description = rs.getString(COLUMN_DESCRIPTION);
            price = rs.getDouble(COLUMN_PRICE);
            salePrice = rs.getDouble(COLUMN_SALE_PRICE);
            shopID = rs.getInt(COLUMN_SHOPID);

            p = new Product(productID, prodName, description, price, salePrice, shopID);
            products.add(p);
        }
        return products;
    }

    public int insertProduct(String pn, String d, double pc, double sp, int sid) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;

        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_PROD_NAME + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_PRICE + ", "
                + COLUMN_SALE_PRICE + ", "
                + COLUMN_SHOPID
                + ") VALUES (?, ?, ?, ?, ?)";

        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, pn);
        stmt.setString(2, d);
        stmt.setDouble(3, pc);
        stmt.setDouble(4, sp);
        if(sid == -1) {
            stmt.setNull(5, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(5, sid);
        }

        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
        }
        return id;
    }

    public boolean deleteProduct(int id) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PRODUCT_ID + " = ?";

        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);

        numRowsAffected = stmt.executeUpdate();

        return (numRowsAffected == 1);
    }

    boolean updateProduct(Product p) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int sid;

        query = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_PROD_NAME + "= ?, "
                + COLUMN_DESCRIPTION + "= ?, "
                + COLUMN_PRICE + "= ?, "
                + COLUMN_SALE_PRICE + "= ?, "
                + COLUMN_SHOPID + "= ?"
                + " WHERE " + COLUMN_PRODUCT_ID + " = ?";

        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, p.getProdName());
        stmt.setString(2, p.getDescription());
        stmt.setDouble(3, p.getPrice());
        stmt.setDouble(4, p.getSalePrice());
        sid = p.getShopID();
        if (sid == -1) {
            stmt.setNull(5, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(5, sid);
        }
        stmt.setInt(6, p.getProductID());

        numRowsAffected = stmt.executeUpdate();

        return (numRowsAffected == 1);
    }

}
