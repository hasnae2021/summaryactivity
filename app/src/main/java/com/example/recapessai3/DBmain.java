package com.example.recapessai3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DB_NAME="orders.db";
    public static final String TABLE_NAME="Sushi";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="name";
    public static final String KEY_CHOICE="choice";
    public static final String KEY_NATURE="nature";
    public static final String KEY_SAUCE="sauce";
    public static final String KEY_TOPPINGS="toppings";

    public static final int VER=1;
    public DBmain(@Nullable Context context) {
        super(context, DB_NAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("create table "+TABLE_NAME+"(" +KEY_ID+"integer primary key,'"+KEY_NAME+"' text, '"+KEY_CHOICE+"' text,'"+KEY_NATURE+"' text,'"+KEY_SAUCE+"'text, '"+KEY_TOPPINGS+"' text)");
        }catch (Exception e){
            Log.e("DBmain","Error when created table",e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+TABLE_NAME+"");
        onCreate(db);
    }
}
