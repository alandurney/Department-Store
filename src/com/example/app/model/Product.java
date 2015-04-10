package com.example.app.model;

public class Product implements Comparable<Product> {

    //making variables that are called into DepartmentStore.java//
    private int productID;
    private String prodName;
    private String description;
    private double price;
    private double salePrice;
    private int storeID;

    //new comment
    public Product(int pid, String pn, String d, double pc, double sp, int sid) {
        this.productID = pid;
        this.prodName = pn;
        this.description = d;
        this.price = pc;
        this.salePrice = sp;
        this.storeID = sid;
    }

    //GET AND SET METHODS//
    public Product(String pn, String d, double pc, double sp, int sid) {
        this(-1, pn, d, pc, sp, sid);
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

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    //this is for the ordering by price.
    //THIS IS DONE THROUGH PROCESS OF MODEL BEING ASSIGNED TO 1 IN THE MAIN APP FILE. IT SORTS BY 
    //CHECKING IF ONE IS GREATER THAN THE ASSIGNED 1 IF IT IS IT GOES BEFORE IT OTHERWISE IT GOES BEHIND.
    @Override
    public int compareTo(Product that) {
        String myName = this.getProdName();
        String yourName = that.getProdName();

        //this is used to compare if one value is greater than the other. the next line is used to return it.
        return myName.compareTo(yourName);
    }
}
