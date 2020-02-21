package com.example.a20zhinengzhizao1;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a20zhinengzhizao1.bean.Chaxun;
import com.example.a20zhinengzhizao1.bean.Gwc;
import com.example.a20zhinengzhizao1.bean.HistoryInfo;
import com.example.a20zhinengzhizao1.bean.Jbxx;
import com.example.a20zhinengzhizao1.bean.Sc;
import com.example.a20zhinengzhizao1.bean.Scxsj;
import com.example.a20zhinengzhizao1.bean.TzItem;
import com.example.a20zhinengzhizao1.bean.Yf;
import com.example.a20zhinengzhizao1.bean.Wddd;
import com.example.a20zhinengzhizao1.bean.Zhaopin;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AppClient extends Application {
    public static  int yz=0;
    private static RequestQueue requestQueue;
    private static SharedPreferences preferences;
    private String[] Name={"全部招聘信息","按岗位查询","按所在地查询","按学历查询","按薪资查询"};

    public List<Zhaopin> getZhaopins() {
        return zhaopins;
    }

    private List<Zhaopin> zhaopins = new ArrayList<>();
    public List<Jbxx> getMjbxx() {
        return mjbxx;
    }

    private List<Jbxx> mjbxx = new ArrayList<>();
    private List<HistoryInfo>  historyInfos=new ArrayList<>();
    public List<Yf> getMyf() {
        return myf;
    }
    public List<HistoryInfo> getHistoryInfos() {
        return historyInfos;
    }

    public List<Wddd> getMyzf() {
        return myzf;
    }


    private List<Wddd> myzf= new ArrayList<>();
    public List<Gwc> getMgwc() {
        return mgwc;
    }

    private List<Gwc> mgwc = new ArrayList<>();
    private List<Yf> myf = new ArrayList<>();
    public List<Sc> getMsc() {
        return msc;
    }

    private List<Sc> msc = new ArrayList<>();
    public List<Chaxun> getmChaxun() {
        return mChaxun;
    }
    private List<TzItem> tzItems;
    private List<Chaxun> mChaxun= new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        LitePal.initialize(this);
        tzItems=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        for (int i=0;i<Name.length;i++)
        {
            mChaxun.add(new Chaxun(Name[i]));
        }
    }
    public static String getUserName()
    {
        return preferences.getString("UserName","");
    }
    public static void setUserName(String UserName)
    {
        preferences.edit().putString("UserName",UserName).apply();
    }

    public static String getPassWord()
    {
        return preferences.getString("PassWord","");
    }
    public static  void setPassWord(String PassWord)
    {
        preferences.edit().putString("PassWord",PassWord).apply();
    }
    public static boolean getXz()
    {
        return preferences.getBoolean("Xz",false);
    }
    public static  void setXz(boolean Xz)
    {
        preferences.edit().putBoolean("Xz",Xz).apply();
    }
    public static void setRequestQueue(JsonObjectRequest jsonObjectRequest){
        requestQueue.add(jsonObjectRequest);
    }

    public static String getId()
    {
        return preferences.getString("Id","");
    }
    public static  void setId(String Id)
    {
        preferences.edit().putString("Id",Id).apply();
    }

    public static String getName()
    {
        return preferences.getString("Name","");
    }
    public static  void setName(String Name)
    {
        preferences.edit().putString("Name",Name).apply();
    }

    public static String getZj()
    {
        return preferences.getString("Zj","");
    }
    public static  void setZj(String Zj)
    {
        preferences.edit().putString("Zj",Zj).apply();
    }

    public static String getZt()
    {
        return preferences.getString("Zt","");
    }
    public static  void setZt(String Zt)
    {
        preferences.edit().putString("Zt",Zt).apply();
    }

    public static int getTime()
    {
        return preferences.getInt("Time",0);
    }
    public static  void setTime(int Time)
    {
        preferences.edit().putInt("Time",Time).apply();
    }

    public static int getZhi()
    {
        return preferences.getInt("Zhi",0);
    }
    public static  void setZhi(int Zhi)
    {
        preferences.edit().putInt("Zhi",Zhi).apply();
    }


    public static boolean getLight()
    {
        return preferences.getBoolean("light",false);
    }
    public static void setLight(boolean light)
    {
        preferences.edit().putBoolean("light",light).apply();
    }

    public static String getKt()
    {
        return preferences.getString("Kt","");
    }
    public static  void setKt(String Kt)
    {
        preferences.edit().putString("Kt",Kt).apply();
    }


    public static boolean getAir()
    {
        return preferences.getBoolean("Air",false);
    }
    public static void setAir(boolean Air)
    {
        preferences.edit().putBoolean("Air",Air).apply();
    }


    public static void add(JsonObjectRequest request){
        requestQueue.add(request);
    }

    public List<TzItem> getTzItems() {
        return tzItems;
    }
    public int getMsgCount(){
        return tzItems.size();
    }


}
