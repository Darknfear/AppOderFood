package com.example.appoderfood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dao.NhanVienDAO;
import com.example.fragment.FragmentDatePicker;
import com.example.fragment.FragmentDatePickerSuaNhanVien;

public class SuaNhanVienActivity extends AppCompatActivity implements View.OnClickListener,View.OnFocusChangeListener {

    EditText edTenDangNhapSua,edNgaySinhSua;
    Button btnSuaNhanVien;

    NhanVienDAO nhanVienDAO;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suanhanvien);

        edNgaySinhSua = findViewById(R.id.ed_NgaySinhSua);
        edTenDangNhapSua = findViewById(R.id.ed_TenDangNhapMonSua);
        btnSuaNhanVien = findViewById(R.id.btn_SuaNhanVien);

        btnSuaNhanVien.setOnClickListener(this);
        edNgaySinhSua.setOnFocusChangeListener(this);

        nhanVienDAO = new NhanVienDAO(this);
    }


    @Override
    public void onClick(View v) {
        if(edTenDangNhapSua.getText().toString().trim().equals("") || edTenDangNhapSua.getText().toString().trim() == null){
            if(edNgaySinhSua.getText().toString().trim().equals("") || edNgaySinhSua.getText().toString().trim() == null){
                Toast.makeText(this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
        }else {

            int maNhanVien = getIntent().getIntExtra("manhanvien",0);
            boolean kiemTra = nhanVienDAO.upDateNhanNhanVienTheoMaNV(maNhanVien,edTenDangNhapSua.getText().toString().trim(),edNgaySinhSua.getText().toString().trim());
            Intent intentData = new Intent();
            intentData.putExtra("kiemtraupdate",kiemTra);
            setResult(Activity.RESULT_OK,intentData);
            finish();

        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        int id = v.getId();
        switch (id){
           case  R.id.ed_NgaySinhSua:
               if (hasFocus){
                   FragmentDatePickerSuaNhanVien datePickerSuaNhanVien = new FragmentDatePickerSuaNhanVien();
                   datePickerSuaNhanVien.show(getSupportFragmentManager(),"Ngày sinh");
               }
               break;
        }
    }
}
