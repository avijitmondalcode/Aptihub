package com.mobitech.maingoquizagain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager2 extends SQLiteOpenHelper {
    private static final String dbName = "login.db";
    private static final String tableName = "user";
    private static final int dbVer = 1;

    public DbManager2(Context context) {
        super(context, dbName, null, dbVer);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table user(email text primary key,pass text,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table if exists user");
        onCreate(sqLiteDatabase);
    }

    public Boolean insert(String email,String pass,String name){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("pass",pass);
        contentValues.put("name",name);

        long result = -1;

        try {
            result = db.insert("user",null,contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(result > -1)
            return true;
        else
            return false;
    }

    public Boolean emailpass(String email,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and pass=?",new String[]{email,pass});
        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
}
