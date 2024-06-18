package com.example.contactapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="contact.db";
    private static final int DATABASE_VERSION=1;

    public DBHelper( Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table contact(id Integer primary key autoincrement,username Text(20) not null,password Text(8) not null,phone Integer(10) not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists contact");

    }
    public Boolean insertData(String username,String password,String phone){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("phone",phone);
        long result=myDB.insert("contact",null,contentValues);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public  Boolean checkusername(String username){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from contact where username=?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }  public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from contact", null);
        return cursor;
    }


    public Boolean checkusenamepassword(String username,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from contact where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return  true;
        }
        else{
            return false;
        }
    }


}
