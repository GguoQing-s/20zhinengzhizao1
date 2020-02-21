package com.example.a20zhinengzhizao1.bean;

import java.util.List;

public class TzItem {
    private int id;
    private List<Msg> messages;
    private String time;
    private boolean read=false;

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public TzItem() {
    }

    public TzItem(int id, List<Msg> messages, String time) {
        this.id = id;
        this.messages = messages;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Msg> getMessages() {
        return messages;
    }

    public void setMessages(List<Msg> messages) {
        this.messages = messages;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
