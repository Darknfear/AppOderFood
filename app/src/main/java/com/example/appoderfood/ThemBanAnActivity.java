package com.example.appoderfood;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dao.BanAnDAO;

public class ThemBanAnActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edThemBanAn;
    Button btnThemBanAn;

    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban_an);

        edThemBanAn =findViewById(R.id.ed_ThemBanAn);
        btnThemBanAn = findViewById(R.id.btn_ThemBanAn);
        btnThemBanAn.setOnClickListener(this);

        banAnDAO = new BanAnDAO(this);
    }

    @Override
    public void onClick(View v) {
        if(edThemBanAn.getText().toString().equals("")||edThemBanAn.getText().toString()!=null){
            boolean hasTenBanAn = banAnDAO.themBanAn(edThemBanAn.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("KiemTra",hasTenBanAn);
            setResult(Activity.RESULT_OK,intent);
            finish();
        }

    }
}
