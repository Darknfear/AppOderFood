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

import com.example.dao.MonAnDAO;

public class SuaMonAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edTenMonSua,edGiaMonSua;
    Button btnSuaMonAn;

    MonAnDAO monAnDAO;

    int maMon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suamonan);

        monAnDAO = new MonAnDAO(this);

        maMon = getIntent().getIntExtra("MaMon",0);
        String tenMon = getIntent().getStringExtra("TenMon");
        String giaMon = getIntent().getStringExtra("GiaMon");

        edTenMonSua = findViewById(R.id.ed_TenMonMuonSua);
        edGiaMonSua = findViewById(R.id.ed_GiaMonSua);
        btnSuaMonAn = findViewById(R.id.btn_SuaMonAn);

        edTenMonSua.setText(tenMon);
        edGiaMonSua.setText(giaMon);

        btnSuaMonAn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(edTenMonSua.getText().toString().trim().equals("") || edTenMonSua.getText().toString().trim() == null){
            if(edGiaMonSua.getText().toString().trim().equals("") || edGiaMonSua.getText().toString().trim() == null){
                Toast.makeText(this,"Vui long nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            }else Toast.makeText(this,"Vui long nhập đủ thông tin",Toast.LENGTH_SHORT).show();
        }else {
            boolean kiemTra = monAnDAO.upDateMonAnTheoMaMon(maMon,edTenMonSua.getText().toString(),edGiaMonSua.getText().toString());
            Intent intentPushData = new Intent();
            intentPushData.putExtra("check",kiemTra);
            setResult(Activity.RESULT_OK,intentPushData);
            finish();
        }
    }
}
