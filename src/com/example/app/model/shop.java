package com.example.app.model;

public class Shop {

    private int shopID;
    private String address;
    private String manFName;
    private String manLName;
    private Int phoneNo;

    public Shop(int shopID, String a, String mf, String ml, int pn) {
        this.shopID = sid;
        this.address = a;
        this.manFName = mf;
        this.manLName = ml;
        this.phoneNo = pn;
    }

    public Shop(String a, String mf, String ml, int pn) {
        this(-1, a, mf, ml, pn);
    }

    public int getId() {
        return shopID;
    }

    public String getName() {
        return name;
    }

    public String getOffice() {
        return office;
    }

    public String getExtension() {
        return extension;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}