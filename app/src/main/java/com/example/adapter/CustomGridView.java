package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appoderfood.HomeActivity;
import com.example.appoderfood.R;
import com.example.dao.BanAnDAO;
import com.example.dao.HoaDonDAO;
import com.example.dto.BanAnDTO;
import com.example.dto.HoaDonDTO;
import com.example.fragment.DanhSachMonAnFragment;
import com.example.fragment.ThucDonFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CustomGridView extends BaseAdapter implements View.OnClickListener{

    Context context;
    int layout;
    List<BanAnDTO> list;
    ViewHolderBanAn viewHolderBanAn;
    BanAnDAO banAnDAO;

    HoaDonDAO hoaDonDAO;

    FragmentManager fragmentManager;

    public CustomGridView(Context context, int layout, List<BanAnDTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        banAnDAO = new BanAnDAO(context);
        hoaDonDAO = new HoaDonDAO(context);
        fragmentManager = ((HomeActivity )context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getMaBan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderBanAn = new ViewHolderBanAn();
            v  = inflater.inflate(R.layout.custom_layout_hienthi_baan,parent,false);

            viewHolderBanAn.imgBanAn = v.findViewById(R.id.img_BanAn);
            viewHolderBanAn.btnOrder = v.findViewById(R.id.btn_Order);
            viewHolderBanAn.btnPay = v.findViewById(R.id.btn_Pay);
            viewHolderBanAn.txtTenBan = v.findViewById(R.id.txt_TenBanAn);

            v.setTag(viewHolderBanAn);
            viewHolderBanAn.imgBanAn.setOnClickListener(this);
            viewHolderBanAn.btnOrder.setOnClickListener(this);

        }else {
            viewHolderBanAn = (ViewHolderBanAn) v.getTag();
        }

        if(list.get(position).isChon()){
            showButton();
        }else {
            anButton();
        }

        BanAnDTO banAnDTO = list.get(position);

        String tinhTrang = banAnDAO.layTinhTrangBan(banAnDTO.getMaBan());
        if(tinhTrang.equals("true")){
            viewHolderBanAn.imgBanAn.setImageResource(R.drawable.banantrue);
        }else {
            viewHolderBanAn.imgBanAn.setImageResource(R.drawable.banan);
        }

        viewHolderBanAn.imgBanAn.setTag(position);
        viewHolderBanAn.txtTenBan.setText(banAnDTO.getTenBan());

        return v;
    }


    public class ViewHolderBanAn{
        ImageView imgBanAn;
        ImageButton btnOrder,btnPay;
        TextView txtTenBan;

    }

    private void showButton(){
        viewHolderBanAn.btnPay.setVisibility(View.VISIBLE);
        viewHolderBanAn.btnOrder.setVisibility(View.VISIBLE);
    }

    private void anButton(){
        viewHolderBanAn.btnPay.setVisibility(View.INVISIBLE);
        viewHolderBanAn.btnOrder.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolderBanAn = (ViewHolderBanAn) ((View)v.getParent()).getTag();
        switch (id){
            case R.id.img_BanAn :
                int vitri = (int) v.getTag();
                list.get(vitri).setChon(true);
                showButton();
                break;
            case R.id.btn_Order :
                int vitri1 = (int) viewHolderBanAn.imgBanAn.getTag();
                int maban = list.get(vitri1).getMaBan();

                String tinhTrang = banAnDAO.layTinhTrangBan(maban);
                if(tinhTrang.equals("false")){
                    //thực hiện code thêm bảng hóa đơn và cập nhật tình trạng bàn :
                    Intent intent = ((HomeActivity) context).getIntent();
                    int maNV = intent.getIntExtra("maNV",0);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
                    String ngayThanhToan = simpleDateFormat.format(calendar.getTime());

                    HoaDonDTO hoaDonDTO = new HoaDonDTO();
                    hoaDonDTO.setMaBan(maban);
                    hoaDonDTO.setMaNhanVien(maNV);
                    hoaDonDTO.setNgayGoi(ngayThanhToan);
                    hoaDonDTO.setTinhTrang("false");

                    long kiemtra = hoaDonDAO.themHoaDon(hoaDonDTO);

                    banAnDAO.capNhatTinhTrangBan(maban,"true");
                    if (kiemtra == 0){
                        Toast.makeText(context,"Them that bai !",Toast.LENGTH_LONG).show();
                    }
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ThucDonFragment thucDonFragment = new ThucDonFragment();
                Bundle bundleDataThucDon = new Bundle();
                bundleDataThucDon.putInt("maban",maban);

                Log.d("banan",maban+"");
                thucDonFragment.setArguments(bundleDataThucDon);
                fragmentTransaction.replace(R.id.fragment_container, thucDonFragment).addToBackStack("hienthibanan").commit();

                break;
        }

    }
}
