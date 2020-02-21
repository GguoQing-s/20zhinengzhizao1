package com.example.a20zhinengzhizao1.bean_z;

import org.litepal.crud.LitePalSupport;

public class TZ_SQL extends LitePalSupport {


    private int id,state;
    private String neirong, time, requestInfo, stris;

    public TZ_SQL(int state,String stris, String neirong, String time) {
        this.state = state;
        this.stris = stris;
        this.neirong = neirong;
        this.time = time;
    }

    public TZ_SQL() {
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    public String getStris() {
        return stris;
    }

    public void setStris(String stris) {
        this.stris = stris;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
