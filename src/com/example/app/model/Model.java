package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static Model getInstance() throws DataAccessException {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    List<Product> products;
    ProductTableGateway productsGateway;

    List<Shop> shops;
    ShopTableGateway shopsGateway;

    private Model() throws DataAccessException {
        try {
            Connection conn = DBConnection.getInstance();
            this.productsGateway = new ProductTableGateway(conn);

            this.products = this.productsGateway.getProducts();

            this.shopsGateway = new ShopTableGateway(conn);
            this.shops = this.shopsGateway.getShops();

        } catch (SQLException ex) {
            throw new DataAccessException("Exception initialising Model Object: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataAccessException("Exception initialising Model Object: " + ex.getMessage());
        }
    }

    public List<Product> getProducts() {
        return this.products;
    }

    //this is used to get products by shop id used as an extension of the //
    public List<Product> getProductsByStoreID(int storeID) {

        List<Product> list = new ArrayList<Product>();
        for (Product p : this.products) {
            if (p.getStoreID() == storeID) {
                list.add(p);
            }
        }
        return list;
    }

    //ADD
    public boolean addProduct(Product p) throws DataAccessException {
        boolean result = false;
        //note: this is where the id details in the database appear.
        // the loop is designed to continue retrieving the info until there is nothing then it ends//
        try {
            int id = this.productsGateway.insertProduct(p.getProdName(), p.getDescription(), p.getPrice(), p.getSalePrice(), p.getStoreID());
            //sets auto increment id//
            if (id != -1) {
                p.setProductID(id);
                this.products.add(p);
                result = true;
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Exception adding product: " + ex.getMessage());
        }
        return result;
    }

    //REMOVE
    public boolean removeProduct(Product p) throws DataAccessException {
        boolean removed = false;

        try {
            removed = this.productsGateway.deleteProduct(p.getProductID());
            if (removed) {
                removed = this.products.remove(p);
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Exception removing product: " + ex.getMessage());
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

//UPDATE. NOTE: THE WORKING ONE WITHOUT ID RETRIEVAL//
    boolean updateProduct(Product p) throws DataAccessException {
        boolean updated = false;

        try {
            updated = this.productsGateway.updateProduct(p);

        } catch (SQLException ex) {
            throw new DataAccessException("Exception updating product: " + ex.getMessage());
        }
        return updated;
    }

    //NOTE REMOVE THIS ADD AFTER
    /*boolean updateProduct (Product p) {
     boolean updated = false;
     //note: this is where the id details in the database appear.
     // the loop is designed to continue retrieving the info until there is nothing then it ends//
     try {
     int id = this.productsGateway.insertProduct(p.getProdName(), p.getDescription(), p.getPrice(), p.getSalePrice(), p.getStoreID());
     if (id != -1) {
     p.setProductID(id);
     this.products.add(p);
     updated = this.productsGateway.updateProduct(p);
     }
     } catch (SQLException ex) {
     throw new DataAccessException("Exception updating Product: " + ex.getMessage());
     }
     return updated;
        
        
     }*/
    ////////////////////////////STORE METHODS///////////////////////////////////
    public List<Shop> getShops() {
        return this.shops;
    }

    //ADD
    public boolean addShop(Shop s) throws DataAccessException {
        boolean result = false;
        try {
            int id = this.shopsGateway.insertShop(s.getShopName(), s.getManFName(), s.getManLName(), s.getPhoneNo());
            if (id != -1) {
                s.setStoreID(id);
                this.shops.add(s);
                result = true;
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Exception Adding Shop: " + ex.getMessage());
        }
        return result;
    }

    boolean updateShop(Shop s) throws DataAccessException {
        boolean updated = false;

        try {
            updated = this.shopsGateway.updateShop(s);

        } catch (SQLException ex) {
            throw new DataAccessException("Exception updating Shop: " + ex.getMessage());
        }
        return updated;
    }

    //REMOVE STORE//
    public boolean removeShop(Shop s) throws DataAccessException {
        boolean removed = false;

        try {
            removed = this.shopsGateway.deleteShop(s.getStoreID());
            if (removed) {
                removed = this.shops.remove(s);
            }
        } catch (SQLException ex) {
            throw new DataAccessException("Exception removing Shop: " + ex.getMessage());
        }
        return removed;
    }

    Shop findShopByStoreID(int storeID) {
        Shop s = null;
        int i = 0;
        boolean found = false;
        while (i < this.shops.size() && !found) {
            s = this.shops.get(i);
            if (s.getStoreID() == storeID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            s = null;
        }
        return s;
    }
}
