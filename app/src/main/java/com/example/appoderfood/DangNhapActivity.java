package com.example.appoderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dao.NhanVienDAO;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edTenDangNhap2,edMatKhau2;
    Button btnDangKy2,btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        addControls();

    }

    private void addControls() {
        edMatKhau2 = findViewById(R.id.edMạKhau2);
        edTenDangNhap2 = findViewById(R.id.edTenDangNhap2);
        btnDangKy2 = findViewById(R.id.btnDangKy2);
        btnDangNhap = findViewById(R.id.btnDangNHap);

        btnDangKy2.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
    }

    private void kiemTraDangNhap(){

        NhanVienDAO nhanVienDAO = new NhanVienDAO(this);
        boolean isHasNV = nhanVienDAO.kiemTraDangNhap(edTenDangNhap2.getText().toString(), edMatKhau2.getText().toString());
        if(isHasNV){
            Intent intent = new Intent(DangNhapActivity.this,HomeActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(DangNhapActivity.this,getResources().getString(R.string.saitendangnhaphoacmatkhau),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNHap :
                kiemTraDangNhap();
                break;
            case R.id.btnDangKy2 :
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
