package com.example.appoderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.database.CreateDataBase;

public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        CreateDataBase createDataBase = new CreateDataBase(this);
    }
}
