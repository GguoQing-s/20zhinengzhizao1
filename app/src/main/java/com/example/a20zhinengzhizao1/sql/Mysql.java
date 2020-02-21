package com.example.a20zhinengzhizao1.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Mysql extends SQLiteOpenHelper {
    private final String CREATE_TABLE="create table jbxx (" +
            "name text," +
            "sex text," +
            "zy text," +
            "school text," +
            "xl text," +
            "experience text," +
            "yx text," +
            "hj text," +
            "email text," +
            "tel text," +
            "birthday text," +
            "jg text," +
            "image blob )";
    private final String CREATE_TABLE2="create table jllb (" +
            "name text," +
            "file text," +
            "bz text," +
            "time text)";
    public Mysql(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
