package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDataBase extends SQLiteOpenHelper {

    public static final String NAME_DATABASE = "OderFood";
    public static final String TABLE_NHANVIEN = "NhanVien";
    public static final String TABLE_MONAN = "MonAn";
    public static final String TABLE_LOAIMONAN = "LoaiMonAn";
    public static final String TABLE_HOADON = "HoaDon";
    public static final String TABLE_CHITIET_HOADON= "ChiTietHoaDon";
    public static final String TABLE_BANAN = "BanAn";
    public static final String TABLE_QUYEN = "LoaiQuyen";

    public static final String TABLE_NHANVIEN_ID = "IDNhanVien";
    public static final String TABLE_NHANVIEN_TENDN = "TenDangNhap";
    public static final String TABLE_NHANVIEN_MK = "MatKhau";
    public static final String TABLE_NHANVIEN_GioiTinh = "GioiTinh";
    public static final String TABLE_NHANVIEN_NGAYSINH = "NgaySinh";
    public static final String TABLE_NHANVIEN_CMND = "CMND";
    public static final String TABLE_NHANVIEN_MAQUYEN = "IDQuyen";

    public static final String TABLE_MONAN_ID = "IDMonAn";
    public static final String TABLE_MONAN_TEN = "TenMon";
    public static final String TABLE_MONAN_GIA = "GiaTien";
    public static final String TABLE_MONAN_IDLOAI = "IDLoai";
    public static final String TABLE_MONAN_HINH = "HinhMonAn";

    public static final String TABLE_LOAIMONAN_ID = "IDLoai";
    public static final String TABLE_LOAIMONAN_TENLOAI = "TenLoai";

    public static final String TABLE_BANAN_ID = "IDBanAn";
    public static final String TABLE_BANAN_TENBAN = "TenBan";
    public static final String TABLE_BANAN_TINHTRANG = "TinhTran";

    public static final String TABLE_HOADON_ID = "IDHoaDon";
    public static final String TABLE_HOADON_IDNHANVIEN = "IDNhanVien";
    public static final String TABLE_HOADON_NGAYLAP = "NgayLap";
    public static final String TABLE_HOADON_TINHTRANG = "TinhTrang";
    public static final String TABLE_HOADON_IDBAN = "IDBanAn";

    public static final String TABLE_CHITIET_HOADON_IDHOADON = "IDHoaDon";
    public static final String TABLE_CHITIET_HOADON_IDMONAN = "IDMonAn";
    public static final String TABLE_CHITIET_HOADON_SOLUONG = "SoLuong";

    public static final String TABLE_QUYEN_ID = "IDQuyen";
    public static final String TABLE_QUYEN_TENQUYEN = "TenQuyen";



    public CreateDataBase( Context context) {
        super(context,NAME_DATABASE,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbNhanVien = "CREATE TABLE IF NOT EXISTS "+TABLE_NHANVIEN+"("+TABLE_NHANVIEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TABLE_NHANVIEN_TENDN+" TEXT,"+
                TABLE_NHANVIEN_MK+" TEXT,"+TABLE_NHANVIEN_GioiTinh+" TEXT,"+TABLE_NHANVIEN_NGAYSINH+" TEXT,"+TABLE_NHANVIEN_CMND+" INT ,"+TABLE_NHANVIEN_MAQUYEN+" INTEGER )";
        String tbBanAn = "CREATE TABLE IF NOT EXISTS "+TABLE_BANAN+"("+TABLE_BANAN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TABLE_BANAN_TENBAN+" TEXT,"+
                TABLE_BANAN_TINHTRANG+" TEXT )";
        String tbMonAn = "CREATE TABLE IF NOT EXISTS "+TABLE_MONAN+"("+TABLE_MONAN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TABLE_MONAN_TEN+" TEXT,"+TABLE_MONAN_IDLOAI+" INT,"+TABLE_MONAN_GIA+" TEXT ,"+ TABLE_MONAN_HINH +" BLOD )";
        String tbLoaiMon = "CREATE TABLE IF NOT EXISTS "+TABLE_LOAIMONAN+"("+TABLE_LOAIMONAN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                TABLE_LOAIMONAN_TENLOAI+" TEXT )";
        String tbHoaDon = "CREATE TABLE IF NOT EXISTS "+TABLE_HOADON+"("+TABLE_HOADON_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TABLE_HOADON_IDBAN +" INTEGER,"+TABLE_HOADON_IDNHANVIEN+" INTEGER,"+TABLE_HOADON_NGAYLAP+" TEXT,"+TABLE_HOADON_TINHTRANG+" TEXT )";
        String tbChiTiet = "CREATE TABLE IF NOT EXISTS "+TABLE_CHITIET_HOADON+"("+TABLE_CHITIET_HOADON_IDHOADON+" INTEGER,"+
                TABLE_CHITIET_HOADON_IDMONAN+" NTEGER,"+TABLE_CHITIET_HOADON_SOLUONG +" INTEGER ,"+
                " PRIMARY KEY ("+TABLE_CHITIET_HOADON_IDMONAN+","+TABLE_CHITIET_HOADON_IDHOADON+"))";
        String tbQuyen = "CREATE TABLE IF NOT EXISTS "+TABLE_QUYEN+"("+TABLE_QUYEN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+TABLE_QUYEN_TENQUYEN+" TEXT )";
        db.execSQL(tbNhanVien);
        db.execSQL(tbBanAn);
        db.execSQL(tbMonAn);
        db.execSQL(tbLoaiMon);
        db.execSQL(tbHoaDon);
        db.execSQL(tbChiTiet);
        db.execSQL(tbQuyen);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS "+NAME_DATABASE;
        db.execSQL(drop);
        onCreate(db);

    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
