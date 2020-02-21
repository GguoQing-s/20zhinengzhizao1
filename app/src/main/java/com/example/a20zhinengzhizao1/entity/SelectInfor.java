package com.example.a20zhinengzhizao1.entity;

public class SelectInfor {
    private String Address;
    private String Bussiness;
    private String Price;
    private String YlName;
    private String Name;
    private String Contact;

    public SelectInfor() {
    }

    public SelectInfor(String address, String bussiness, String price, String ylName, String name, String contact) {
        Address = address;
        Bussiness = bussiness;
        Price = price;
        YlName = ylName;
        Name = name;
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBussiness() {
        return Bussiness;
    }

    public void setBussiness(String bussiness) {
        Bussiness = bussiness;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getYlName() {
        return YlName;
    }

    public void setYlName(String ylName) {
        YlName = ylName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
