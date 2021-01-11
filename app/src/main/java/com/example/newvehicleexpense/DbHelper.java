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

        db.execSQL("create table userdetailx (item text,note text,date text,ammount number,type text)");

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

        long result=db.insert("userDetailx",null,contentValues);
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

        Cursor cursor=db.rawQuery("select * from userdetailx",null);

        return  cursor;
    }
    public Cursor getDatabyDate(String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from userdetailx where date=?",new String[]{date},null);

        return  cursor;
    }

    public  Boolean deleteUserData(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from userdetailx where item=?",new String[]{name});
        if(cursor.getCount()>0)
        {
            long result=db.delete("userdetailx","item=?",new String[]{name});
            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }

        }
        else {
            return false;
        }}
}
