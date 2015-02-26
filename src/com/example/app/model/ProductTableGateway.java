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
    
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_PRODUCT_ID = "productID";
    //new comment
    private static final String COLUMN_PROD_NAME = "prodName";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_SALE_PRICE = "salePrice";
    private int COLUMN_ID;
    
    public ProductTableGateway(Connection connection) {
        mConnection = connection;
    }
    //this is a comment
    public List<Product> getProducts() throws SQLException{
        String query;
        Statement stmt;
        ResultSet rs;
        List<Product> products;
        
        //VARIABLES FOR EACH COLUMN
        String prodName, description;
        int productID, id;
        double price, salePrice;
        
        Product p;
        
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);
        
        
        products = new ArrayList<Product>();
        while (rs.next()){
            id = rs.getInt(COLUMN_ID);
            productID = rs.getInt(COLUMN_PRODUCT_ID);
            prodName = rs.getString(COLUMN_PROD_NAME);
            description = rs.getString(COLUMN_DESCRIPTION);
            price = rs.getDouble(COLUMN_PRICE);
            salePrice = rs.getDouble(COLUMN_SALE_PRICE);
            
            p = new Product(id, productID, prodName, description, price, salePrice);
            products.add(p);
        }
        return products;
    }
    
        public int insertProduct(int pid, String pn, String d, double pc, double sp)throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id = -1;
        
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_PRODUCT_ID + ", " +
                COLUMN_PROD_NAME + ", " +
                COLUMN_DESCRIPTION + ", " +
                COLUMN_PRICE + ", " +
                COLUMN_SALE_PRICE + 
                ") VALUES (?, ?, ?, ?, ?)";
        
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, pid);
        stmt.setString(2, pn);
        stmt.setString(3, d);
        stmt.setDouble(4, pc);
        stmt.setDouble(5, sp);
        
        numRowsAffected = stmt.executeUpdate();
        if(numRowsAffected = 1){
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            
            id = keys.getInt(1);
        }
            return id;
        }
    
    public boolean deleteProduct(int id) throws SQLException{
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "DELETE FROM " + TABLE_NAME + "WHERE " + COLUMN_PRODUCT_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, id);
        
        numRowsAffected = stmt.executeUpdate();
        
        return(numRowsAffected == 1);
    }
    
    boolean updateProduct(Product p){ throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_PROD_NAME     + "= ?, " +
                COLUMN_DESCRIPTION    + "= ?, " +
                COLUMN_PRICE     + "= ?, " +
                COLUMN_SALE_PRICE     + "= ?" +
                " WHERE " + COLUMN_PRODUCT_ID + " = ?";
        
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, p.getProdName());
        stmt.setString(2, p.getDescription());
        stmt.setString(3, p.getPrice());
        stmt.setString(4, p.getSalePrice());
        
        numRowsAffected = stmt.executeUpdate();
        
        return (numRowsAffected == 1);
    }
    
}
