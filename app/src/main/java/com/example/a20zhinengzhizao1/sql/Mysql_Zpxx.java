package com.example.a20zhinengzhizao1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Mysql_Zpxx extends SQLiteOpenHelper {
    private final  String CREATE_TABLE="create table fbzp (" +
            "id text," +
            "name text," +
            "address text," +
            "tel text," +
            "email text," +
            "gw text," +
            "xz text," +
            "zyyq text," +
            "jlyz text," +
            "gwyz text," +
            "com_address text)";
    public Mysql_Zpxx(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
