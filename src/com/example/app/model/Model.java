package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    List<Product> products;
    ProductTableGateway productsGateway;

    List<Shop> shops;
    ShopTableGateway shopsGateway;

    private Model() {
        try {
            Connection conn = DBConnection.getInstance();
            this.productsGateway = new ProductTableGateway(conn);

            this.products = this.productsGateway.getProducts();

            this.shopsGateway = new ShopTableGateway(conn);
            this.shops = this.shopsGateway.getShops();

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Product> getProducts() {
        return this.products;
    }

    //ADD
    public boolean addProduct(Product p) {
        boolean result = false;
        try {
            int id = this.productsGateway.insertProduct(p.getProdName(), p.getDescription(), p.getPrice(), p.getSalePrice(), p.getShopID());
            if (id != -1) {
                p.setProductID(id);
                this.products.add(p);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    //REMOVE
    public boolean removeProduct(Product p) {
        boolean removed = false;

        try {
            removed = this.productsGateway.deleteProduct(p.getProductID());
            if (removed) {
                removed = this.products.remove(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    Product findProductByProductID(int productID) {
        Product p = null;
        int i = 0;
        boolean found = false;
        while (i < this.products.size() && !found) {
            p = this.products.get(i);
            if (p.getProductID() == productID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }

//UPDATE
    boolean updateProduct(Product p) {
        boolean updated = false;

        try {
            updated = this.productsGateway.updateProduct(p);

        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

    ////////////////////////////STORE METHODS///////////////////////////////////
    public List<Shop> getShops() {
        return this.shops;
    }
    
    //ADD
    public boolean addShop(Shop s) {
        boolean result = false;
        try {
            int id = this.shopsGateway.insertShop(s.getAddress(), s.getManFName(), s.getManLName(), s.getPhoneNo());
            if (id != -1) {
                s.setShopID(id);
                this.shops.add(s);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
