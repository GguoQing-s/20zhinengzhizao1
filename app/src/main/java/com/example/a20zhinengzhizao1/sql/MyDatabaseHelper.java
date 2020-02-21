package com.example.a20zhinengzhizao1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static  final  String CREATA_BOOK="create table User (id Integer primary key autoincrement,username text,password text,email text,ida text,jine text)";
    public  static  final  String CREATE_CATEGORY="create table Logon (id Integer primary key autoincrement,username text,password text,password1 text,email text)";
    public  static  final  String CREATE_ZHAOPIN="create table Zhopin (id Integer primary key autoincrement,name text,type text," +
            "name1 text,yaoqiu text" +
            ",chengshi text,xiuli text,xinzi text" +
            ",email text,time text)";

    private Context mcontext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
        mcontext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATA_BOOK);
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_ZHAOPIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Logon");
        db.execSQL("drop table if exists Zhopin");
        onCreate(db);
    }
}
