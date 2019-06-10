package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.amitshekhar.DebugDB;
import com.example.database.CreateDataBase;
import com.example.dto.ChiTietHoaDonDTO;
import com.example.dto.HoaDonDTO;
import com.example.dto.ThanhToanDTO;

import java.util.ArrayList;
import java.util.List;

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
    public long layMaGoiMonTheoMaBan(int maBan,String tinhTrang){
        String truyVan = " SELECT * FROM "+CreateDataBase.TABLE_HOADON+" WHERE "+ CreateDataBase.TABLE_HOADON_IDBAN + " = '" +maBan +"' AND "+
                CreateDataBase.TABLE_HOADON_TINHTRANG + " = '" + tinhTrang +"'";
        long maHoaDon = 0;
        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            maHoaDon = cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_HOADON_ID));

            cursor.moveToNext();
        }

        return maHoaDon;
    }
    public boolean kiemTraMonAnDaTonTai(int maHoaDon,int maMonAn){

        String truyVan = " SELECT * FROM "+CreateDataBase.TABLE_CHITIET_HOADON+" WHERE "+CreateDataBase.TABLE_CHITIET_HOADON_IDHOADON+" = '"+
                maHoaDon+"' AND "+CreateDataBase.TABLE_CHITIET_HOADON_IDMONAN+" = '"+maMonAn+"'";
        Cursor cursor = database.rawQuery(truyVan,null);
        if (cursor.getCount() != 0) return true;
        else return false;

    }
    public int laySoLuongMonAnTheoMaHoaDon(int maHoaDon,int maMonAn){
        int soLuong = 0;

        String truyVan = " SELECT * FROM "+CreateDataBase.TABLE_CHITIET_HOADON+" WHERE "+CreateDataBase.TABLE_CHITIET_HOADON_IDHOADON+" = '"+
                maHoaDon+"' AND "+CreateDataBase.TABLE_CHITIET_HOADON_IDMONAN+" = '"+maMonAn+"'";
        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            soLuong = cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_CHITIET_HOADON_SOLUONG));
            cursor.moveToNext();
        }


        return soLuong;
    }

    public boolean upDateSoLuong(ChiTietHoaDonDTO chiTietHoaDonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_CHITIET_HOADON_SOLUONG,chiTietHoaDonDTO.getSoLuong());
        long kiemTra = database.update(CreateDataBase.TABLE_CHITIET_HOADON,contentValues,CreateDataBase.TABLE_CHITIET_HOADON_IDHOADON+" = '"+
                chiTietHoaDonDTO.getMaHoaDon()+"'"+" AND "+CreateDataBase.TABLE_CHITIET_HOADON_IDMONAN+" = '"+chiTietHoaDonDTO.getMaMonAn()+"'",null);
        if(kiemTra != 0) return true;
        else return false;

    }
    public boolean themChiTietGoimon(ChiTietHoaDonDTO chiTietHoaDonDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_CHITIET_HOADON_SOLUONG,chiTietHoaDonDTO.getSoLuong());
        contentValues.put(CreateDataBase.TABLE_CHITIET_HOADON_IDHOADON,chiTietHoaDonDTO.getMaHoaDon());
        contentValues.put(CreateDataBase.TABLE_CHITIET_HOADON_IDMONAN,chiTietHoaDonDTO.getMaMonAn());

        long kiemTra = database.insert(CreateDataBase.TABLE_CHITIET_HOADON,null,contentValues);
        if(kiemTra != 0) return true;
        else return false;
    }

    public List<ThanhToanDTO> layDanhSachMonAnTheoHoaDon(int maHoaDon){

        List<ThanhToanDTO> thanhToanDTOS = new ArrayList<>();

        String truyVan = " SELECT * FROM "+CreateDataBase.TABLE_CHITIET_HOADON+","+CreateDataBase.TABLE_MONAN+" WHERE "
                +CreateDataBase.TABLE_CHITIET_HOADON+"."+CreateDataBase.TABLE_CHITIET_HOADON_IDMONAN+" = "
                +CreateDataBase.TABLE_MONAN+"."+CreateDataBase.TABLE_MONAN_ID+
                " AND "+CreateDataBase.TABLE_CHITIET_HOADON_IDHOADON+" = '"+maHoaDon+"'";

        Cursor cursor = database.rawQuery(truyVan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();
            thanhToanDTO.setTenMon(cursor.getString(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_TEN)));
            Log.d("tenmon",thanhToanDTO.getTenMon().toString());
            thanhToanDTO.setSoLuong(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_CHITIET_HOADON_SOLUONG)));
            thanhToanDTO.setGiaTien(cursor.getInt(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_GIA)));

            thanhToanDTOS.add(thanhToanDTO);
            cursor.moveToNext();
        }
        return thanhToanDTOS;
    }

    public boolean capNhatTinhTrangHoaDon(int maBan,String tinhTrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDataBase.TABLE_HOADON_TINHTRANG,tinhTrang);

        long kiemTra = database.update(CreateDataBase.TABLE_HOADON,contentValues,CreateDataBase.TABLE_HOADON_IDBAN+" = '"+maBan+"'",null);
        if(kiemTra != 0){
            return true;
        }else return false;
    }
}
