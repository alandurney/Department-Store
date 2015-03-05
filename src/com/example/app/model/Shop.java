package com.example.app.model;

public class Shop {

    private int shopID;
    private String address;
    private String manFName;
    private String manLName;
    private int phoneNo;

    public Shop(int sid, String a, String mf, String ml, int pn) {
        this.shopID = sid;
        this.address = a;
        this.manFName = mf;
        this.manLName = ml;
        this.phoneNo = pn;
    }

    public Shop(String a, String mf, String ml, int pn) {
        this(-1, a, mf, ml, pn);
    }

    public int getShopID() {
        return shopID;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getManFName() {
        return manFName;
    }
    
    public void setManFName(String manFName) {
        this.manFName = manFName;
    }


    public String getManLName() {
        return manLName;
    }
    
    public void setManLName(String manLName) {
        this.manLName = manLName;
    }
    
    public int getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }  
}