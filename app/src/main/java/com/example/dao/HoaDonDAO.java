package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.CreateDataBase;
import com.example.dto.HoaDonDTO;

public class HoaDonDAO {
    SQLiteDatabase database;
    public HoaDonDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }

    public long themHoaDon(HoaDonDTO hoaDonDTO){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_HOADON_IDBAN,hoaDonDTO.getMaBan());
        contentValues.put(CreateDataBase.TABLE_HOADON_IDNHANVIEN,hoaDonDTO.getMaNhanVien());
        contentValues.put(CreateDataBase.TABLE_HOADON_NGAYLAP,hoaDonDTO.getNgayGoi());
        contentValues.put(CreateDataBase.TABLE_HOADON_TINHTRANG,hoaDonDTO.getTinhTrang());

        long kiemtra = database.insert(CreateDataBase.TABLE_HOADON,null,contentValues);

        return kiemtra;
    }
}
