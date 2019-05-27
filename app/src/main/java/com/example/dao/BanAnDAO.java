package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.CreateDataBase;
import com.example.dto.BanAnDTO;

import java.util.ArrayList;
import java.util.List;

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

    public List<BanAnDTO> getDanhSachBanAn(){
        List<BanAnDTO> list = new ArrayList<>();
        String truyVan = "SELECT * FROM "+ CreateDataBase.TABLE_BANAN;
        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_BANAN_ID)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_BANAN_TENBAN)));

            list.add(banAnDTO);
            cursor.moveToNext();
        }
        return list;
    }
}
