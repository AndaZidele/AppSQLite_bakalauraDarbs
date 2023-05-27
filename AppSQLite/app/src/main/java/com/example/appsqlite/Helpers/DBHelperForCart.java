package com.example.appsqlite.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appsqlite.Adapters.CartsAdapter;

public class DBHelperForCart extends SQLiteOpenHelper {
    //    public DBHelper(Context context){
//        super(context, "AppDB",null,1);
//    }
    public DBHelperForCart(Context context) {
        super(context, "AppDB.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDB){
        myDB.execSQL("create Table user(id INTEGER PRIMARY KEY AUTOINCREMENT , email Text, name Text,  password Text, phone Text, address Text)");


        myDB.execSQL("create Table product(id INTEGER PRIMARY KEY AUTOINCREMENT , name Text, special_offer Text, price Text, description Text, image Text, category Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop Table if exists user");
        myDB.execSQL("drop Table if exists product");
    }
}
