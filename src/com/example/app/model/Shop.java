package com.example.app.model;

public class Shop implements Comparable<Shop> {

    //making variables that are called into DepartmentStore.java//
    private int storeID;
    private String shopName;
    private String manFName;
    private String manLName;
    private int phoneNo;

    //new comment
    public Shop(int sid, String sn, String mfn, String mln , int pn) {
        this.storeID = sid;
        this.shopName = sn;
        this.manFName = mfn;
        this.manLName = mln;
        this.phoneNo = pn;
    }

    //GET AND SET METHODS//
    public Shop(String sn, String mfn, String mln, int pn) {
        this(-1, sn, mfn, mln, pn);
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    //this is for the ordering by price.
    //THIS IS DONE THROUGH PROCESS OF MODEL BEING ASSIGNED TO 1 IN THE MAIN APP FILE. IT SORTS BY 
    //CHECKING IF ONE IS GREATER THAN THE ASSIGNED 1 IF IT IS IT GOES BEFORE IT OTHERWISE IT GOES BEHIND.
    @Override
    public int compareTo(Shop that) {
        String myName = this.getShopName();
        String yourName = that.getShopName();

        //this is used to compare if one value is greater than the other. the next line is used to return it.
        return myName.compareTo(yourName);
    }
}
