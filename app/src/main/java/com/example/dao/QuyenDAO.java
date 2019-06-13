package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adapter.CustomGridView;
import com.example.database.CreateDataBase;
import com.example.dto.QuyenDTO;

import java.util.ArrayList;
import java.util.List;

public class QuyenDAO {
    SQLiteDatabase database;

    public QuyenDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }

    public void themQuyen(String tenQuyen){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_QUYEN_TENQUYEN,tenQuyen);

        database.insert(CreateDataBase.TABLE_QUYEN,null,contentValues);
    }

    public List<QuyenDTO> listQuyenDTO(){
        List<QuyenDTO> quyenDTOS = new ArrayList<>();
        String truyVan = "SELECT * FROM "+CreateDataBase.TABLE_QUYEN;
        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            QuyenDTO quyenDTO = new QuyenDTO();
            quyenDTO.setMaQuyen(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_QUYEN_ID)));
            quyenDTO.setTenQuyen(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_QUYEN_TENQUYEN)));

            quyenDTOS.add(quyenDTO);

            cursor.moveToNext();
        }
        return quyenDTOS;
    }

}
