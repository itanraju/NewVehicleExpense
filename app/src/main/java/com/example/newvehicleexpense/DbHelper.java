package com.example.newvehicleexpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "userdbx", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table userdetailx (id INTEGER PRIMARY KEY AUTOINCREMENT,item text,note text,date text,ammount text,type text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists userdetailx");

    }

    public  Boolean inserUserData(String item,String note,String date,String ammount,String type)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("item",item);
        contentValues.put("note",note);
        contentValues.put("date",date);
        contentValues.put("ammount",ammount);
        contentValues.put("type",type);

        long result=db.insert("userdetailx",null,contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public Cursor viewData()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from userdetailx group by date",null);

        return  cursor;
    }

    public Cursor getTotal()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        final Cursor rawQuery = db.rawQuery("SELECT SUM(ammount) AS total FROM userdetailx", null);

        return rawQuery;
    }

    public Cursor viewDataAll()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from userdetailx",null);

        return  cursor;
    }


    public void deleteTable()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from userdetailx");
    }

    public Cursor getDatabyDate(String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from userdetailx where date=? group by date",new String[]{date},null);

        return  cursor;
    }

}
