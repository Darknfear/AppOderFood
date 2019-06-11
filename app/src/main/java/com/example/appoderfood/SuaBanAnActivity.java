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

import com.example.dao.BanAnDAO;

public class SuaBanAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtTenBanSua;
    Button btnSuaBanAn;

    BanAnDAO banAnDAO;

    int maBan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suabanan);

        txtTenBanSua = findViewById(R.id.ed_TenBanAnMuonSua);
        btnSuaBanAn = findViewById(R.id.btn_SuaBanAn);

        banAnDAO = new BanAnDAO(this);

        btnSuaBanAn.setOnClickListener(this);

        maBan = getIntent().getIntExtra("maban",0);
        if(maBan != 0){

        }
    }

    @Override
    public void onClick(View v) {
        boolean kiemTra = banAnDAO.capNhatLaiBanAnTheoMa(maBan,txtTenBanSua.getText().toString());
        if(txtTenBanSua.getText().toString().trim().equals("") || txtTenBanSua.getText().toString() == null){

            Toast.makeText(this,"Nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();
            intent.putExtra("kiemtra",kiemTra);
            setResult(Activity.RESULT_OK,intent);
            finish();

        }
    }
}
