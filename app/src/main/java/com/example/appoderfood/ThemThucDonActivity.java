package com.example.appoderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapter.CustomSpiner;
import com.example.dao.LoaiMonAnDAO;
import com.example.dao.MonAnDAO;
import com.example.dto.LoaiMonAnDTO;
import com.example.dto.MonAnDTO;

import java.util.ArrayList;
import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnThemLoaiThucDon;
    Spinner sploaiThucDon;
    ImageView imgAnhMon;
    Button btnThemThucDon,btnThoatThemThucDon;
    EditText edTenMon,edGiaTien;

    private static final int REQUEST_CODE_THEM = 112;
    private static final int REQUEST_CODE_MO_MANHINH = 113;

    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAnDTO> list;
    CustomSpiner adapter;

    MonAnDAO monAnDAO;

    String sDuongDanHinh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        monAnDAO = new MonAnDAO(this);

        imgAnhMon = findViewById(R.id.img_AnhMon);
        btnThemLoaiThucDon = findViewById(R.id.btn_ThemLoaiMon);
        sploaiThucDon = findViewById(R.id.sp_LoaiMon);
        hienThiSpinnerLoaiMonAn();
        btnThemThucDon = findViewById(R.id.btn_DongYThemMon);
        btnThoatThemThucDon = findViewById(R.id.btn_ThoatThemMonAn);
        edGiaTien = findViewById(R.id.ed_Gia);
        edTenMon = findViewById(R.id.ed_TenMon);

        btnThemLoaiThucDon.setOnClickListener(this);
        imgAnhMon.setOnClickListener(this);
        btnThemThucDon.setOnClickListener(this);
        btnThoatThemThucDon.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_ThemLoaiMon :
                Intent intent = new Intent(ThemThucDonActivity.this,ThemLoaiThucDonActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEM);
                break;
            case R.id.img_AnhMon :
                Intent moHinh = new Intent();
                moHinh.setType("image/*");
                moHinh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(moHinh,"Chọn hình thực đơn"),REQUEST_CODE_MO_MANHINH);
                break;
            case R.id.btn_DongYThemMon :
                int vitri = sploaiThucDon.getSelectedItemPosition();
                int maLoai = list.get(vitri).getMaLoai();
                if(edTenMon.getText().toString().equals("")||edTenMon.getText().toString() ==null){
                    if(edGiaTien.getText().toString().equals("")||edGiaTien.getText().toString() == null){
                        Toast.makeText(ThemThucDonActivity.this,"Vui long nhập đủ thông tin",Toast.LENGTH_LONG).show();
                    }else  Toast.makeText(ThemThucDonActivity.this,"Vui long nhập đủ thông tin",Toast.LENGTH_LONG).show();
                }else {
                    MonAnDTO  monAnDTO = new MonAnDTO();
                    monAnDTO.setGia(edGiaTien.getText().toString());
                    monAnDTO.setIdLoaiMon(maLoai);
                    monAnDTO.setTenMon(edTenMon.getText().toString());
                    monAnDTO.setHinhAnh(sDuongDanHinh);
                    boolean kiemtra = monAnDAO.insertMonAn(monAnDTO);
                    if(kiemtra){
                        Toast.makeText(this,"Thêm thành công",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.btn_ThoatThemMonAn :
                finish();
                break;
        }

    }

    private void hienThiSpinnerLoaiMonAn(){
       list = loaiMonAnDAO.getListLoaiMonAn();
       adapter = new CustomSpiner(ThemThucDonActivity.this,R.layout.custom_layout_loaithucdon_spiner,list);
       sploaiThucDon.setAdapter(adapter);
       adapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_THEM&&resultCode == Activity.RESULT_OK){
            Intent intent = data;
            boolean kiemtra = intent.getBooleanExtra("Kiemtraloaithucdon",false);
            if(kiemtra){
                hienThiSpinnerLoaiMonAn();
                Toast.makeText(this,"Thêm thành công !",Toast.LENGTH_LONG).show();

            }else Toast.makeText(this,"Thêm thất bại !",Toast.LENGTH_LONG).show();

        }else if(requestCode == REQUEST_CODE_MO_MANHINH && resultCode == Activity.RESULT_OK){
            sDuongDanHinh = data.getData().toString();
            imgAnhMon.setImageURI(data.getData());
        }
    }
}
