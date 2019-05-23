package com.example.appoderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
        startActivity(intent);
    }
}
