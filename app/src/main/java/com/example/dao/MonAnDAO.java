package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.database.CreateDataBase;
import com.example.dto.MonAnDTO;

public class MonAnDAO {
    SQLiteDatabase database;
    public MonAnDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database  = createDataBase.open();
    }

    public boolean insertMonAn(MonAnDTO monAnDTO){
        String tryVan = "INSERT INTO "+CreateDataBase.TABLE_MONAN + " VALUES (null,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(tryVan);
        statement.clearBindings();

        statement.bindString(1,monAnDTO.getTenMon());
        statement.bindString(2,monAnDTO.getGia());
        statement.bindDouble(3, (double) monAnDTO.getIdLoaiMon());
        statement.bindBlob(4,monAnDTO.getHinhAnh());

        statement.executeInsert();

        return true;

    }


}
