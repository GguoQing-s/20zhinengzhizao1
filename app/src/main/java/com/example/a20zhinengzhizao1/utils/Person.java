package com.example.a20zhinengzhizao1.utils;

public class Person {
    private String name;
    private String py;

    public Person(String name) {
        this.name = name;
        this.py=PinYinUtils.getPinYin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", py='" + py + '\'' +
                '}';
    }
}
