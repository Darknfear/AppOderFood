package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.BitmapFactory;

import com.example.database.CreateDataBase;
import com.example.dto.MonAnDTO;

import java.util.ArrayList;
import java.util.List;

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
        statement.bindDouble(2,monAnDTO.getIdLoaiMon());
        statement.bindString(3, monAnDTO.getGia());
        statement.bindBlob(4,monAnDTO.getHinhAnh());

        statement.executeInsert();

        return true;

    }
    public List<MonAnDTO> layDanhSachMonAnTheoLoai(int ma){
        List<MonAnDTO> dsMonAnDTO = new ArrayList<>();
        String truyvan = "SELECT * FROM "+CreateDataBase.TABLE_MONAN+" WHERE "+CreateDataBase.TABLE_MONAN_IDLOAI+" = '"+ma+"'";
        Cursor cursor =  database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonAnDTO monAnDTO = new MonAnDTO();

            monAnDTO.setTenMon(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_TEN)));
            monAnDTO.setGia(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_GIA)));
            monAnDTO.setHinhAnh(cursor.getBlob(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_HINH)));
            monAnDTO.setId(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_ID)));
            monAnDTO.setIdLoaiMon(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_IDLOAI)));

            dsMonAnDTO.add(monAnDTO);

            cursor.moveToNext();
        }
        return dsMonAnDTO;
    }


}
