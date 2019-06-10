package com.example.appoderfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.example.adapter.AdapterThanhToan;
import com.example.dao.BanAnDAO;
import com.example.dao.HoaDonDAO;
import com.example.dto.ThanhToanDTO;
import com.example.fragment.HomeFragment;
import com.example.fragment.ThucDonFragment;

import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lvThanhToian;
    List<ThanhToanDTO> thanhToanDTOList;
    AdapterThanhToan adapterThanhToan;

    HoaDonDAO hoaDonDAO ;
    BanAnDAO banAnDAO;

    int sum = 0;
    int maBan;
    int maHoaDon;

    Button btnDongYThanhToan,btnThoatThanhToan;
    TextView txtTongTien;

    FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        lvThanhToian = findViewById(R.id.lv_ThanhToan);
        btnDongYThanhToan = findViewById(R.id.btn_DongYThanhToan);
        btnThoatThanhToan = findViewById(R.id.btn_ThoatThanhToan);
        txtTongTien = findViewById(R.id.txt_TongTien);

        fragmentManager = getSupportFragmentManager();

        btnDongYThanhToan.setOnClickListener(this);
        btnThoatThanhToan.setOnClickListener(this);

        banAnDAO = new BanAnDAO(this);
        hoaDonDAO = new HoaDonDAO(this);

        Intent intentLayMaBan = getIntent();
        maBan = intentLayMaBan.getIntExtra("maban",0);
        if (maBan != 0){

            hienThiThanhToan();

            for(int i = 0;i<thanhToanDTOList.size();i++){
                sum += thanhToanDTOList.get(i).getSoLuong() * thanhToanDTOList.get(i).getGiaTien();
            }
            txtTongTien.setText("Tổng tiền :"+sum);

        }
    }

    private void hienThiThanhToan(){
        maHoaDon = (int) hoaDonDAO.layMaGoiMonTheoMaBan(maBan,"false");
        thanhToanDTOList = hoaDonDAO.layDanhSachMonAnTheoHoaDon(maHoaDon);
        adapterThanhToan = new AdapterThanhToan(this,R.layout.custom_layout_thanhtoan,thanhToanDTOList);
        lvThanhToian.setAdapter(adapterThanhToan);
        adapterThanhToan.notifyDataSetChanged();

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_DongYThanhToan:
                boolean kiemTraBanAn = banAnDAO.capNhatTinhTrangBan(maBan,"false");
                boolean kiemTraHoaDon = hoaDonDAO.capNhatTinhTrangHoaDon(maBan,"true");
                if(kiemTraBanAn && kiemTraHoaDon){
                    Toast.makeText(this,"Thanh toán thành công",Toast.LENGTH_LONG).show();
                    hienThiThanhToan();
                    txtTongTien.setText("Tổng tiền :");
                }else {
                    Toast.makeText(this,"Thanh toán thất bại",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_ThoatThanhToan:
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
        }

    }
}
