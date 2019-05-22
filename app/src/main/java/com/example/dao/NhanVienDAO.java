package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.CreateDataBase;
import com.example.dto.NhanVienDTO;

public class NhanVienDAO {
    SQLiteDatabase database;
    public NhanVienDAO(Context context) {
        CreateDataBase createDataBase = new CreateDataBase(context);
        database = createDataBase.open();
    }

    public long insertNhanVien(NhanVienDTO nhanVienDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_TENDN,nhanVienDTO.getTenDangNhap());
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_MK,nhanVienDTO.getMatKhau());
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_GioiTinh,nhanVienDTO.getGioiTinh());
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_NGAYSINH,nhanVienDTO.getNgaySinh());
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_CMND,nhanVienDTO.getSoCMND());

        long kiemTra = database.insert(CreateDataBase.TABLE_NHANVIEN,null,contentValues);
        return kiemTra;
    }


}
