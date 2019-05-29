package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.CreateDataBase;
import com.example.dto.LoaiMonAnDTO;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonAnDAO {

    SQLiteDatabase database;

    public LoaiMonAnDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }

    public boolean insertLoaiMonAn(String tenLoai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_LOAIMONAN_TENLOAI,tenLoai);

        long kiemTra = database.insert(CreateDataBase.TABLE_LOAIMONAN,null,contentValues);
        if(kiemTra!=0){
            return true;
        }else return false;

    }

    public List<LoaiMonAnDTO> getListLoaiMonAn(){
        List<LoaiMonAnDTO> listLoaiMon = new ArrayList<>();
        String truyvan = "SELECT * FROM "+CreateDataBase.TABLE_LOAIMONAN;
        Cursor cursor =  database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_LOAIMONAN_ID)));
            loaiMonAnDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_LOAIMONAN_TENLOAI)));

            listLoaiMon.add(loaiMonAnDTO);
            cursor.moveToNext();
        }

        return listLoaiMon;
    }
}
