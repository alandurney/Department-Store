package com.example.app.model;

public class Product {
    
    //making variables that are called into DepartmentStore.java//
    private int productID;
    private String prodName;
    private String description;
    private double price;
    private double salePrice;
    
    //new comment
    public Product(int pid, String pn, String d, double pc, double sp){
        this.productID = pid;
        this.prodName = pn;
        this.description = d;
        this.price = pc;
        this.salePrice = sp;
    }
    
    
    //GET AND SET METHODS//
    
    public Product(String pn, String d, double pc, double sp) {
        this(-1, pn, d, pc, sp);
    }
    
    public int getProductID() {
        return productID;
    }
    
    public void setProductID(int productID) {
        this.productID = productID;   
    }
    
    public String getProdName() {
        return prodName;
    }
    
    public void setProdName(String prodName) {
        this.prodName = prodName;   
    }
    
      public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;   
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;   
    }
    
    public double getSalePrice() {
        return salePrice;
    }
    
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;   
    }
}
