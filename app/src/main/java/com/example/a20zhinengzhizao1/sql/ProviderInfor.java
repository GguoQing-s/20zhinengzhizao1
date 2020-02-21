package com.example.a20zhinengzhizao1.sql;



import com.example.a20zhinengzhizao1.utils.PinYinUtils;

import org.litepal.crud.LitePalSupport;

public class ProviderInfor extends LitePalSupport {
    private int id;
    private int Number;
    private String Name;
    private String City;
    private String Address;
    private String Legal_person;
    private String Contacts;
    private String Business;
    private String tel;
    private double price;
    private byte[] Photo;
    private String py_name,py_contact;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProviderInfor() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        py_name= PinYinUtils.getPinYin(name);
        Name = name;
    }

    public ProviderInfor(int number, String name, String city, String address, String legal_person, String contacts, String business, byte[] photo) {
        Number = number;
        Name = name;
        City = city;
        Address = address;
        Legal_person = legal_person;
        Contacts = contacts;
        Business = business;
        Photo = photo;
       py_name= PinYinUtils.getPinYin(name);
        py_contact=PinYinUtils.getPinYin(contacts);
    }

    public String getPy_name() {

        return py_name;
    }

    public String getPy_contact() {
        return py_contact;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLegal_person() {
        return Legal_person;
    }

    public void setLegal_person(String legal_person) {
        Legal_person = legal_person;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts)
    {
        py_contact= PinYinUtils.getPinYin(contacts);
        Contacts = contacts;
    }

    public String getBusiness() {
        return Business;
    }

    public void setBusiness(String business) {
        Business = business;
    }

    public byte[] getPhoto() {
        return Photo;
    }

    public void setPhoto(byte[] photo) {
        Photo = photo;
    }


}
