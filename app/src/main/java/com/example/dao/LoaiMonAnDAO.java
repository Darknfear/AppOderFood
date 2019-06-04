package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

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
    public Bitmap getHinhMonAn(int ma){

        Bitmap bitmap = null;
        String truyvan = "SELECT * FROM "+CreateDataBase.TABLE_MONAN+" JOIN "+CreateDataBase.TABLE_LOAIMONAN+" ON "
                +CreateDataBase.TABLE_MONAN+"."+CreateDataBase.TABLE_MONAN_IDLOAI+" = "+CreateDataBase.TABLE_LOAIMONAN+"."+CreateDataBase.TABLE_LOAIMONAN_ID
                +" WHERE "+ CreateDataBase.TABLE_MONAN_IDLOAI+ "= '"+ma+"' ORDER BY "+CreateDataBase.TABLE_MONAN_ID +" LIMIT 1";
        Cursor cursor =  database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            byte[] hinhAnh = cursor.getBlob(cursor.getColumnIndex(CreateDataBase.TABLE_MONAN_HINH));
            bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
            cursor.moveToNext();
        }
      return bitmap;
    }
}
