package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.CreateDataBase;
import com.example.dto.MonAnDTO;

public class MonAnDAO {
    SQLiteDatabase database;
    public MonAnDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database  = createDataBase.open();
    }

    public boolean insertMonAn(MonAnDTO monAnDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_MONAN_TEN,monAnDTO.getTenMon());
        contentValues.put(CreateDataBase.TABLE_MONAN_GIA,monAnDTO.getGia());
        contentValues.put(CreateDataBase.TABLE_MONAN_IDLOAI,monAnDTO.getIdLoaiMon());
        contentValues.put(CreateDataBase.TABLE_MONAN_HINH,monAnDTO.getHinhAnh());

        long kiemTra = database.insert(CreateDataBase.TABLE_MONAN,null,contentValues);

        if(kiemTra != 0){
            return true;
        }else return false;
    }
}
