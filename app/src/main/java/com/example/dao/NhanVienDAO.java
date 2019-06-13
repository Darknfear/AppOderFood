package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.database.CreateDataBase;
import com.example.dto.NhanVienDTO;

import java.util.ArrayList;
import java.util.List;

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

    public int kiemTraDangNhap(String tenDangNhap,String matKhau){

        int maNhanVien = 0;

        String truyVan = "SELECT * FROM "+CreateDataBase.TABLE_NHANVIEN+" WHERE "+CreateDataBase.TABLE_NHANVIEN_TENDN+" = '"+tenDangNhap+
                "'  AND "+CreateDataBase.TABLE_NHANVIEN_MK+" = '"+matKhau+"'";
        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            maNhanVien = cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_NHANVIEN_ID));
            cursor.moveToNext();
        }
        return maNhanVien;
    }

    public List<NhanVienDTO> listNhanVien(){

        List<NhanVienDTO> nhanVienDTOS = new ArrayList<>();

        String truyVan = " SELECT * FROM "+CreateDataBase.TABLE_NHANVIEN;
        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            NhanVienDTO nhanVienDTO = new NhanVienDTO();

            nhanVienDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_NHANVIEN_ID)));
            Log.d("data",nhanVienDTO.getMaNV()+"");
            nhanVienDTO.setTenDangNhap(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_NHANVIEN_TENDN)));
            nhanVienDTO.setNgaySinh(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_NHANVIEN_NGAYSINH)));

            nhanVienDTOS.add(nhanVienDTO);

            cursor.moveToNext();
        }
        return nhanVienDTOS;
    }

    public boolean xoaNhanVienTheoMa(int maNhanVien){

        long kiemTra = database.delete(CreateDataBase.TABLE_NHANVIEN,CreateDataBase.TABLE_NHANVIEN_ID+" = '"+maNhanVien+"'",null);
        if (kiemTra != 0){
            return true;
        }else return false;
    }
    public boolean upDateNhanNhanVienTheoMaNV(int maNhanVien,String tenNhanVien,String ngaySinh){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_TENDN,tenNhanVien);
        contentValues.put(CreateDataBase.TABLE_NHANVIEN_NGAYSINH,ngaySinh);

        long kiemTra = database.update(CreateDataBase.TABLE_NHANVIEN,contentValues,CreateDataBase.TABLE_NHANVIEN_ID+" = '"+maNhanVien+"'",null);
        if(kiemTra != 0) return true;
        else return false;
    }


}
