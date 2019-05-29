package com.example.appoderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapter.CustomSpiner;
import com.example.dao.LoaiMonAnDAO;
import com.example.dto.LoaiMonAnDTO;

import java.util.ArrayList;
import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btnThemLoaiThucDon;
    Spinner sploaiThucDon;

    private static final int REQUEST_CODE_THEM = 112;

    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAnDTO> list;
    CustomSpiner adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        btnThemLoaiThucDon = findViewById(R.id.btn_ThemLoaiMon);
        sploaiThucDon = findViewById(R.id.sp_LoaiMon);
        hienThiSpinnerLoaiMonAn();

        btnThemLoaiThucDon.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_ThemLoaiMon :
                Intent intent = new Intent(ThemThucDonActivity.this,ThemLoaiThucDonActivity.class);
                startActivityForResult(intent,REQUEST_CODE_THEM);
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

        }
    }
}
