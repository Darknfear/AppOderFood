package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.CreateDataBase;

public class BanAnDAO  {
    SQLiteDatabase database;

    public BanAnDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }

    public boolean themBanAn(String tenBan){
        ContentValues values = new ContentValues();
        values.put(CreateDataBase.TABLE_BANAN_TENBAN,tenBan);
        values.put(CreateDataBase.TABLE_BANAN_TINHTRANG,"false");

        long kiemTra = database.insert(CreateDataBase.TABLE_BANAN,null,values);

        if(kiemTra != 0){
            return true;
        }else return false;
    }
}
