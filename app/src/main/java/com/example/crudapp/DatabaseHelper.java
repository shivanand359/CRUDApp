package com.example.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shiva.db";
    private static final String TABLE_NAME = "shiva_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "GENDER";
    private static final String COL_4 = "ROLLNO";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query;
        Query="CREATE TABLE shiva_table(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENDER TEXT,ROLLNO INTEGER)";
        db.execSQL(Query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String NAME,String GENDER,String ROLLNO)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,GENDER);
        contentValues.put(COL_4,ROLLNO);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean UpdateData(String ID,String NAME ,String GENDER,String ROLLNO)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,NAME);
        contentValues.put(COL_3,GENDER);
        contentValues.put(COL_4,ROLLNO);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ID});

        return true;

    }
    public Integer deleteData(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{ID});

    }
}
