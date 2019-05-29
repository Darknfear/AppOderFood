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

import com.example.dao.LoaiMonAnDAO;
import com.example.dto.LoaiMonAnDTO;
import com.example.dto.NhanVienDTO;

public class ThemLoaiThucDonActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edThemLoaiMon;
    Button btnThemLoaiMon;
    LoaiMonAnDAO loaiMonAnDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaithucdon);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        edThemLoaiMon = findViewById(R.id.ed_ThemLoaiMonAn);
        btnThemLoaiMon = findViewById(R.id.btn_ThemLoaiMonAn);

        btnThemLoaiMon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

                if(edThemLoaiMon.getText().toString() == null||edThemLoaiMon.getText().toString().equals("")){
                    Toast.makeText(ThemLoaiThucDonActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_LONG).show();


                }else {
                    boolean hasTenLoai = loaiMonAnDAO.insertLoaiMonAn(edThemLoaiMon.getText().toString());
                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK,intent);
                    intent.putExtra("Kiemtraloaithucdon", hasTenLoai);
                    finish();
                }
    }
}
