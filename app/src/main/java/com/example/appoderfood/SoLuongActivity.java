package com.example.appoderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dao.HoaDonDAO;
import com.example.dto.ChiTietHoaDonDTO;

public class SoLuongActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edThemSoLuongMon;
    Button btnDongYThemSoLuong;

    int maBan;
    int maMonAn;

    HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsoluong);

        hoaDonDAO = new HoaDonDAO(this);

        Intent layDataIntent = getIntent();
        maBan = layDataIntent.getIntExtra("maban",0);
        maMonAn = layDataIntent.getIntExtra("mamonan",0);


        edThemSoLuongMon = findViewById(R.id.ed_SoLuongMon);
        btnDongYThemSoLuong = findViewById(R.id.btn_ThemSoLuongMon);

        btnDongYThemSoLuong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int maHoaDon = (int) hoaDonDAO.layMaGoiMonTheoMaBan(maBan,"false");
        boolean kiemTra = hoaDonDAO.kiemTraMonAnDaTonTai(maHoaDon,maMonAn);
        if(kiemTra){
            // tiến hành update lại
            int soLuongCu = hoaDonDAO.laySoLuongMonAnTheoMaHoaDon(maHoaDon,maMonAn);
            int soLuongMoi = Integer.parseInt(edThemSoLuongMon.getText().toString());
            int tongSoLuong = soLuongCu + soLuongMoi;

            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            chiTietHoaDonDTO.setMaHoaDon(maHoaDon);
            chiTietHoaDonDTO.setMaMonAn(maMonAn);
            chiTietHoaDonDTO.setSoLuong(tongSoLuong);

            boolean check = hoaDonDAO.upDateSoLuong(chiTietHoaDonDTO);
            if(check){
                Toast.makeText(this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_LONG).show();
            }else Toast.makeText(this,getResources().getString(R.string.themthatbai),Toast.LENGTH_LONG).show();


        }else {
            // tạo mới số lượng
            int soLuong = Integer.parseInt(edThemSoLuongMon.getText().toString());

            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            chiTietHoaDonDTO.setMaHoaDon(maHoaDon);
            chiTietHoaDonDTO.setMaMonAn(maMonAn);
            chiTietHoaDonDTO.setSoLuong(soLuong);

            boolean check = hoaDonDAO.themChiTietGoimon(chiTietHoaDonDTO);
            if(check){
                Toast.makeText(this,"Thêm thành công",Toast.LENGTH_LONG).show();
            }else Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_LONG).show();


        }
    }
}
