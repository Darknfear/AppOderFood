package com.example.appoderfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dao.NhanVienDAO;
import com.example.database.CreateDataBase;
import com.example.dto.NhanVienDTO;
import com.example.fragment.FragmentDatePicker;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener , View.OnFocusChangeListener {

    EditText edTenDangNhap,edMatKhau,edNgaySinh,edSoCMND;
    Button btnDangKy,btnQuayLai;
    RadioGroup rdGroup;
    String gioiTinh;

    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);

        addControls();
        addEvents();

    }

    private void addEvents() {

    }

    private void addControls() {

        edMatKhau = findViewById(R.id.edMatKhau);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edNgaySinh = findViewById(R.id.edNgaySinh);
        edSoCMND = findViewById(R.id.edSCMND);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnQuayLai =findViewById(R.id.btnQuayLai);
        rdGroup = findViewById(R.id.radGroup);

        edNgaySinh.setOnFocusChangeListener(this);
        btnDangKy.setOnClickListener(this);
        btnQuayLai.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangKy :
               String sTenDangNhap = edTenDangNhap.getText().toString();
               String sMatKhau = edMatKhau.getText().toString();
                switch (rdGroup.getCheckedRadioButtonId()){
                    case R.id.rad_Nam:
                        gioiTinh = "Nam";
                        break;
                    case R.id.rad_Nu:
                        gioiTinh = "Nữ";
                        break;
                }
                String sNgaySinh = edNgaySinh.getText().toString();
                String sSoCMND = edSoCMND.getText().toString();

                if(sTenDangNhap==null||sTenDangNhap.equals("")){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.nhapdumatkhau),Toast.LENGTH_LONG).show();
                }else if(sMatKhau==null||sMatKhau.equals("")){
                    Toast.makeText(DangKyActivity.this,getResources().getString(R.string.nhaptendangnhap),Toast.LENGTH_LONG).show();
                }else {
                    NhanVienDTO nhanVienDTO = new NhanVienDTO();
                    nhanVienDTO.setTenDangNhap(sTenDangNhap);
                    nhanVienDTO.setMatKhau(sMatKhau);
                    nhanVienDTO.setNgaySinh(sNgaySinh);
                    nhanVienDTO.setGioiTinh(gioiTinh);
                    nhanVienDTO.setSoCMND(Integer.parseInt(sSoCMND));

                    nhanVienDAO = new NhanVienDAO(this);
                    long kiemtra = nhanVienDAO.insertNhanVien(nhanVienDTO);
                    if(kiemtra !=0){
                        Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthanhcong),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(DangKyActivity.this,getResources().getString(R.string.themthatbai),Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case R.id.btnQuayLai :
                Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id= v.getId();
        switch (id){
            case R.id.edNgaySinh:
                if(hasFocus){
                    FragmentDatePicker datePicker = new FragmentDatePicker();
                    datePicker.show(getSupportFragmentManager(),"Ngày sinh");
                }
                break;
        }
    }
}
