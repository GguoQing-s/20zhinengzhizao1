package com.example.a20zhinengzhizao1.entity;



import com.example.a20zhinengzhizao1.utils.PinYinUtils;

import org.litepal.crud.LitePalSupport;

public class ContactInfor extends LitePalSupport {
    private String Name;
    private String sex;
    private String BirthDay;
    private String Tel;
    private String Produce;
    private String Post;
    private String Email;
    private String Address;
    private String py;
    private String isCheck="false";
    private boolean isVisiable=false;

    public boolean isVisiable() {
        return isVisiable;
    }

    public void setVisiable(boolean visiable) {
        isVisiable = visiable;
    }

    public String isCheck() {
        return isCheck;
    }

    public void setCheck(String check) {
        isCheck = check;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public ContactInfor(String name, String sex, String birthDay, String tel, String produce, String post, String email, String address) {
        Name = name;
        py= PinYinUtils.getPinYin(name);
        this.sex = sex;
        BirthDay = birthDay;
        Tel = tel;
        Produce = produce;
        Post = post;
        Email = email;
        Address = address;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        py= PinYinUtils.getPinYin(name);
        Name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getProduce() {
        return Produce;
    }

    public void setProduce(String produce) {
        Produce = produce;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }

    public ContactInfor() {
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
