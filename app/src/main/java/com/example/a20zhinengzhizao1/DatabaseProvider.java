package com.example.a20zhinengzhizao1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.a20zhinengzhizao1.sql.MyDatabaseHelper;

public class DatabaseProvider extends ContentProvider {

    public static  final int BOOK_DIR=0;
    public static  final  int BOOK_ITEM=1;
    public static  final int CATEGORY_DIR=2;
    public static  final int CATEGORY_ITEM=3;
    public static  final int CATEGORY_ZHAOPIN=4;
    public static  final int CATEGORY_FABU=4;
    public static  final  String AUTHORITY="com.example.databasetest.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;
    static {
        uriMatcher  =new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"User",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"User/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"Logon",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"Logon/#",CATEGORY_ITEM);
        uriMatcher.addURI(AUTHORITY,"Zhopin",CATEGORY_ZHAOPIN);
        uriMatcher.addURI(AUTHORITY,"fbzp",CATEGORY_FABU);
    }






    @Override
    public boolean onCreate() {
        dbHelper  =new MyDatabaseHelper(getContext(),"BookStore.db",null,3);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
         SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri))
        {

            case CATEGORY_ZHAOPIN:
                cursor=db.query("Zhopin",projection,selection,selectionArgs,null,null,sortOrder);
                break;

            case BOOK_DIR:
                cursor=db.query("User",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookid = uri.getPathSegments().get(1);
                cursor =db.query("User",projection,"id=?"
                        ,new String[]{bookid},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Logon",projection,selection,selectionArgs
                        ,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor =db.query("Logon",projection,"id=?",
                        new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn =null;
        switch (uriMatcher.match(uri))
        {


            case CATEGORY_ZHAOPIN:
                long newZhopinId = db.insert("Zhopin",null,values);
                uriReturn  =Uri.parse("content://"+AUTHORITY+"/User/"+
                        newZhopinId);
                break;

            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("User",null,values);
                uriReturn  =Uri.parse("content://"+AUTHORITY+"/User/"+
                        newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId =db.insert("Logon",null,values);
                uriReturn =Uri.parse("content://"+AUTHORITY+"/Logon/"+
                        newCategoryId);
                break;
            default:
                break;

        }
        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows=0;
        switch (uriMatcher.match(uri))
        {

            case CATEGORY_ZHAOPIN:
                updatedRows  =db.update("Zhopin",values,selection,selectionArgs);
                break;


            case BOOK_DIR:
                updatedRows  =db.update("User",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId=  uri.getPathSegments().get(1);
                updatedRows  =db.update("User",values,"id=?",new String[]
                        {bookId});
                break;
            case CATEGORY_DIR:
                updatedRows  =db.update("Logon",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows  =db.update("Logon",values,"id=?",new String[]
                        {categoryId});
                break;
            default:
                break;
        }
        return updatedRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri))
        {

            case CATEGORY_ZHAOPIN:
                deleteRows  =db.delete("Zhopin",selection,selectionArgs);
                break;

            case BOOK_DIR:
                deleteRows  =db.delete("User",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows  =db.delete("User","id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows  =db.delete("Logon",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows  =db.delete("Logon","id=?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return  deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri))
        {

            case CATEGORY_ZHAOPIN:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.Zhopin";

            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.User";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.User";

            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category";
        }
        return null;
    }
}
